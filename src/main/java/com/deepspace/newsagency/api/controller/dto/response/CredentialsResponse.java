package com.deepspace.newsagency.api.controller.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CredentialsResponse extends AbstractDtoResponse {

    private String token;

    private String refreshToken;

    private long expiresIn;

    public static CredentialsResponse of(String token, long expiresIn, String refreshToken) {
        CredentialsResponse response = new CredentialsResponse();
        response.setToken(token);
        response.setExpiresIn(expiresIn);
        response.setRefreshToken(refreshToken);
        return response;
    }
}
