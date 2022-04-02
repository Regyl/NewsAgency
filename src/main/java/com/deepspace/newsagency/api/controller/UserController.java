package com.deepspace.newsagency.api.controller;

import com.deepspace.newsagency.annotation.Loggable;
import com.deepspace.newsagency.api.controller.dto.request.Credentials;
import com.deepspace.newsagency.api.controller.dto.request.TokenDto;
import com.deepspace.newsagency.api.controller.dto.request.UserDto;
import com.deepspace.newsagency.api.controller.dto.response.CredentialsResponse;
import com.deepspace.newsagency.api.controller.dto.response.UserDtoResponse;
import com.deepspace.newsagency.entity.User;
import com.deepspace.newsagency.exception.EntityAlreadyExistsException;
import com.deepspace.newsagency.exception.InvalidRefreshTokenException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.deepspace.newsagency.Utils;
import com.deepspace.newsagency.api.mapper.UserMapper;
import com.deepspace.newsagency.mail.EmailService;
import com.deepspace.newsagency.security.jwt.JwtProvider;
import com.deepspace.newsagency.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Loggable
@Tag(name = "Authorization")
@Validated
@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService service;
    private final UserMapper mapper;
    private final EmailService emailService;
    private final long tokenLifetime;

    public UserController(@Value("${jwt.tokenLifetime}") Long tokenLifetime,
                          AuthenticationManager authenticationManager,
                          JwtProvider jwtProvider,
                          UserService service,
                          UserMapper mapper,
                          EmailService emailService) {
        this.tokenLifetime = tokenLifetime;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.service = service;
        this.mapper = mapper;
        this.emailService = emailService;
    }

    @PostMapping("/refresh")
    @Operation(summary = "Get new access/refresh tokens")
    public CredentialsResponse refreshAccessToken(@RequestBody @Valid TokenDto token) {
        String refreshToken = token.getRefreshToken();
        User user = service.findByRefreshToken(refreshToken);
        jwtProvider.validateToken(refreshToken);
        String login = jwtProvider.getLoginFromToken(refreshToken);
        if(!login.equals(user.getLogin())) {
            throw new InvalidRefreshTokenException(refreshToken);
        }
        String newAccessToken = jwtProvider.generateAccessToken(login);
        String newRefreshToken = jwtProvider.generateRefreshToken(login);
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getLogin(), null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        user.setRefreshToken(newRefreshToken);
        service.save(user);
        return CredentialsResponse.of(newAccessToken, tokenLifetime, newRefreshToken);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Authorization")
    public CredentialsResponse signIn(@RequestBody @Valid Credentials credentials) {
        Authentication auth = new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword());
        authenticationManager.authenticate(auth);
        String accessToken = jwtProvider.generateAccessToken(credentials.getLogin());
        String refreshToken = jwtProvider.generateRefreshToken(credentials.getLogin());
        User user = service.findByLogin(credentials.getLogin());
        user.setRefreshToken(refreshToken);
        service.save(user);
        return CredentialsResponse.of(accessToken, tokenLifetime, refreshToken);
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Registration")
    public UserDtoResponse signUp(@RequestBody @Valid UserDto dto) {
        if(service.isExists(dto.getLogin())) {
            throw new EntityAlreadyExistsException(User.class, dto.getLogin());
        }

        User user = mapper.toEntity(dto);
        String temporaryKey = UUID.randomUUID().toString();
        user.setTemporaryKey(temporaryKey);
        emailService.doSend(dto.getLogin(),
                "Glad to see you!", "http://195.2.214.167:8082/auth/"+temporaryKey); //fixme сделать профили и запускать с параметрами
        user = service.save(user);
        return mapper.toDto(user);
    }

    @GetMapping("/info")
    @Operation(summary = "Account information")
    public UserDtoResponse getData() {
        User user = Utils.getAuthenticatedUser();
        return mapper.toDto(user);
    }

    @GetMapping("/auth/{value}")
    public void verifyEmail(@PathVariable String value) {
        User user = service.findByTemporaryKey(value);
        mapper.verify(user);
        service.save(user);
    }

}
