package org.idk.newsagency.api.controller.dto.request;

import lombok.Data;
import org.idk.newsagency.entity.enumeration.Role;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserDto {

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Set<Role> authorities;
}
