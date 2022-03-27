package com.deepspace.newsagency.api.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class Credentials {

    @NotNull
    @Email
    private String login;

    @NotNull
    private String password;
}
