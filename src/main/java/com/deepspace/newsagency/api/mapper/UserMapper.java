package com.deepspace.newsagency.api.mapper;

import com.deepspace.newsagency.annotation.Mapper;
import com.deepspace.newsagency.api.controller.dto.request.UserDto;
import com.deepspace.newsagency.api.controller.dto.response.UserDtoResponse;
import com.deepspace.newsagency.entity.Authority;
import com.deepspace.newsagency.entity.User;
import com.deepspace.newsagency.entity.enumeration.Role;
import com.deepspace.newsagency.service.AuthorityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Mapper
public class UserMapper extends AbstractMapper<User, UserDto> {

    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;

    public UserMapper(ModelMapper mapper, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        super(mapper);
        this.passwordEncoder=passwordEncoder;
        this.authorityService=authorityService;
    }

    @Override
    public User toEntity(UserDto dto) {
        User user = mapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Authority> authorities = Set.of(authorityService.findByRole(Role.UNVERIFIED_USER));
        user.setEmailVerified(false);
        user.setAuthorities(authorities);
        return user;
    }

    @Override
    public UserDtoResponse toDto(User dto) {
        return mapper.map(dto, UserDtoResponse.class);
    }

    public void verify(User user) {
        user.getAuthorities().clear();
        user.getAuthorities().add(authorityService.findByRole(Role.USER));

        user.setTemporaryKey(null);
        user.setEmailVerified(true);
    }
}
