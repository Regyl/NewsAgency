package com.deepspace.newsagency.api.controller.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentDtoResponse {

    private UUID announcementId;

    private UUID userId;

    private String text;

    private LocalDateTime creationTimestamp;
}
