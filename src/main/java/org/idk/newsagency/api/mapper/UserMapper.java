package org.idk.newsagency.api.mapper;

import org.idk.newsagency.annotation.Mapper;
import org.idk.newsagency.api.controller.dto.request.UserDto;
import org.idk.newsagency.api.controller.dto.response.UserDtoResponse;
import org.idk.newsagency.entity.Authority;
import org.idk.newsagency.entity.User;
import org.idk.newsagency.entity.enumeration.Role;
import org.idk.newsagency.service.AuthorityService;
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
//        Set<Authority> authorities = Set.of(authorityService.findByRole(dto.getAuthorities().stream().findFirst().get())); //TODO: fix
        Set<Authority> authorities = Set.of(authorityService.findByRole(Role.USER));
        user.setAuthorities(authorities);
        return user;
    }

    @Override
    public UserDtoResponse toDto(User dto) {
        return mapper.map(dto, UserDtoResponse.class);
    }
}
