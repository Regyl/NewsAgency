package org.idk.newsagency.api.mapper;

import lombok.extern.java.Log;
import org.idk.newsagency.annotation.Mapper;
import org.idk.newsagency.api.controller.dto.response.AnnouncementDtoResponse;
import org.idk.newsagency.api.controller.dto.request.AnnouncementDto;
import org.idk.newsagency.entity.Announcement;
import org.idk.newsagency.entity.Location;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.Base64;

@Log
@Mapper
public class AnnouncementMapper extends AbstractMapper<Announcement, AnnouncementDto> {

    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    public AnnouncementMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public Announcement toEntity(AnnouncementDto dto) {
        Announcement announcement = mapper.map(dto, Announcement.class);

        try {
            announcement.setImage(ENCODER.encodeToString(dto.getImage().getBytes()));
        } catch (IOException e) {
            log.warning(e.getMessage());
        }

        announcement.setLocation(new Location(dto.getLatitude(), dto.getLongitude()));
        return announcement;
    }

    @Override
    public AnnouncementDtoResponse toDto(Announcement announcement) {
        return mapper.map(announcement, AnnouncementDtoResponse.class);
    }
}
