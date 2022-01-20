package org.idk.newsagency.api.controller.dto.reponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AbstractDtoResponse {

    private LocalDateTime timestamp;

    AbstractDtoResponse() {
        this.timestamp = LocalDateTime.now();
    }
}
