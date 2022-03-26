package org.idk.newsagency.api.controller.dto.request;

import lombok.Data;
import org.idk.newsagency.entity.enumeration.Role;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserDto {

    @NotNull
    @Email(message = "login must be an email")
    private String login;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    private MultipartFile avatar;
}
