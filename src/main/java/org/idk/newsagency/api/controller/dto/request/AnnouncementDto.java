package org.idk.newsagency.api.controller.dto.request;

import lombok.Data;
import org.idk.newsagency.entity.Location;
import org.idk.newsagency.entity.enumeration.Section;
import org.idk.newsagency.entity.enumeration.Status;

import javax.validation.constraints.NotNull;

@Data
public class AnnouncementDto {

    @NotNull
    private Section section;

    @NotNull
    private Status status;

    @NotNull
    private String header;

    @NotNull
    private String description;

    @NotNull
    private String text;

    @NotNull
    private String image;

    @NotNull
    private Location location;
}
