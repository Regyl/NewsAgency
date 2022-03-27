package com.deepspace.newsagency.api.controller.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AbstractDtoResponse {

    private Info info;

    AbstractDtoResponse() {
        this.info = new Info();
    }

    @Data
    static class Info {

        private LocalDateTime timestamp;

        Info() {
            this.timestamp = LocalDateTime.now();
        }
    }
}
