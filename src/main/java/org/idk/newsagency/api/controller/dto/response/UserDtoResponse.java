package org.idk.newsagency.api.controller.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.idk.newsagency.entity.enumeration.Role;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDtoResponse extends AbstractDtoResponse {

    private UUID id;

    private String login;

    private String firstName;

    private String lastName;

    private Set<AuthorityDtoResponse> authorities;

    @Data
    private static class AuthorityDtoResponse {

        private Role role;

    }
}
