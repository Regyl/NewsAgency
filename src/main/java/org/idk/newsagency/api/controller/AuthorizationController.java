package org.idk.newsagency.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.idk.newsagency.api.controller.dto.request.Credentials;
import org.idk.newsagency.api.controller.dto.request.UserDto;
import org.idk.newsagency.api.controller.dto.response.CredentialsResponse;
import org.idk.newsagency.api.controller.dto.response.UserDtoResponse;
import org.idk.newsagency.api.mapper.UserMapper;
import org.idk.newsagency.entity.User;
import org.idk.newsagency.security.service.JwtProvider;
import org.idk.newsagency.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authorization")
@Validated //TODO: what is it
@RestController
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthorizationController(AuthenticationManager authenticationManager,
                                   JwtProvider jwtProvider, UserService userService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Authorization")
    public CredentialsResponse signIn(@RequestBody Credentials credentials) {
        Authentication auth = new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword());
        authenticationManager.authenticate(auth);
        String token = jwtProvider.generateToken(credentials.getLogin());
        return CredentialsResponse.of(token);
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Registration")
    public UserDtoResponse signUp(@RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        user = userService.save(user);
        return userMapper.toDto(user);
    }
}
