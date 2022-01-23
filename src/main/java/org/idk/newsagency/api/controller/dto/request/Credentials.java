package org.idk.newsagency.api.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Credentials {

    @NotNull
    private String login;

    @NotNull
    private String password;
}
