package com.deepspace.newsagency.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class JwtProvider {

    private static final String BEARER = "Bearer";

    private final long refreshTokenLifetime;
    private final long tokenLifetime;
    private final Key key;
    private final JwtParser parser;
    private final UserDetailsService userDetailsService;

    public JwtProvider(@Value("${jwt.secret}") String jwtSecret,
                       @Value("${jwt.tokenLifetime}") Long tokenLifetime, //in hours
                       @Value("${jwt.refresh.tokenLifetime}") Long refreshTokenLifetime, //in hours
                       UserDetailsService userDetailsService) {

        this.tokenLifetime = tokenLifetime;
        this.refreshTokenLifetime = refreshTokenLifetime;

        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        this.parser = Jwts.parserBuilder().setSigningKey(this.key).build();
        this.userDetailsService = userDetailsService;
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, refreshTokenLifetime);
    }

    public String generateAccessToken(String username) {
        return generateToken(username, tokenLifetime);
    }

    public void validateToken(String token)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        parser.parseClaimsJws(token);
    }

    @Nullable
    public String getToken(HttpServletRequest request) {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearer != null && bearer.startsWith(BEARER)) {
            return bearer.substring(BEARER.length());
        } else {
            return null;
        }
    }

    public Authentication getAuthentication(String token) {;
        UserDetails userDetails = userDetailsService.loadUserByUsername(token);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String generateToken(String username, long expiration) {
        LocalDateTime now = new Date().toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime expireAt = now.plusHours(expiration);
        Date expirationDate = Date.from(expireAt.toInstant(ZoneOffset.UTC));

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public String getLoginFromToken(String token) {
        return parser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
