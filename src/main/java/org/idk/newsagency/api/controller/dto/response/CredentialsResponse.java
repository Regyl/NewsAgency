package org.idk.newsagency.api.controller.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CredentialsResponse extends AbstractDtoResponse {

    private String token;

    public static CredentialsResponse of(String token) {
        CredentialsResponse response = new CredentialsResponse();
        response.setToken(token);
        return response;
    }
}
