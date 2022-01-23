package org.idk.newsagency.security.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtProvider {

    private static final String BEARER = "Bearer";

    private final long tokenLifetime;
    private final Key key;
    private final JwtParser parser;
    private final CustomUserDetailsService userDetailsService;

    public JwtProvider(@Value("${jwt.secret}") String jwtSecret,
                       @Value("${tokenLifetime}") Long tokenLifetime,
                       CustomUserDetailsService userDetailsService) {
        this.tokenLifetime = tokenLifetime;

        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        this.parser = Jwts.parserBuilder().setSigningKey(this.key).build();
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String login) {
        LocalDateTime now = new Date().toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime expireAt = now.plusMinutes(tokenLifetime);
        Date expiration = Date.from(expireAt.toInstant(ZoneOffset.UTC));

        return Jwts.builder()
                .setSubject(login)
                .setExpiration(expiration)
                .signWith(key)
                .setId(UUID.randomUUID().toString())
                .compact();
    }

    public boolean validateToken(String token)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        parser.parseClaimsJws(token);
        return true;
    }

    public String getLoginFromToken(String token) {
        return parser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
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

    public Authentication getAuthentication(String login) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
