package com.deepspace.newsagency.api.controller.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepspace.newsagency.entity.enumeration.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDtoResponse extends AbstractDtoResponse {

    private UUID id;

    private String login;

    private String firstName;

    private String lastName;

    private Boolean emailVerified;

    private MultipartFile avatar;

    private Set<AuthorityDtoResponse> authorities;

    @Data
    private static class AuthorityDtoResponse {

        private Role role;

    }
}
