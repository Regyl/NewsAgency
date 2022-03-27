package com.deepspace.newsagency.api.controller.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LikeDtoResponse extends AbstractDtoResponse {

    private UUID announcementId;

    private UUID userId;

    private LocalDateTime creationTimestamp;
}
