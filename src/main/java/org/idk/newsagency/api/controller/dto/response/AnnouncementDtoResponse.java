package org.idk.newsagency.api.controller.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.idk.newsagency.entity.Location;
import org.idk.newsagency.entity.enumeration.Section;
import org.idk.newsagency.entity.enumeration.Status;

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

}
