package com.deepspace.newsagency.api.controller.dto.request;

import lombok.Data;
import com.deepspace.newsagency.entity.enumeration.Section;
import com.deepspace.newsagency.entity.enumeration.Status;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile image;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    /*@JsonIgnore TODO:
    private LocationDto location;

    @Data
    static final class LocationDto {

        @NotNull
        private Long latitude;

        @NotNull
        private Long longitude;
    }*/
}
