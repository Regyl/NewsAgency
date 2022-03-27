package com.deepspace.newsagency.api.controller.dto.response;

import com.deepspace.newsagency.entity.enumeration.Section;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepspace.newsagency.entity.Location;
import com.deepspace.newsagency.entity.enumeration.Status;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnnouncementDtoResponse extends AbstractDtoResponse {

    private UUID id;

    private Section section;

    private Status status;

    private String header;

    private String description;

    private String text;

    private byte[] image;

    private UUID userId;

    private Location location;

    private int likeQuantity;

    private int commentQuantity;
}
