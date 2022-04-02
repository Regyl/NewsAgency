package com.deepspace.newsagency.api.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenDto {

    @NotBlank
    private String refreshToken;
}
