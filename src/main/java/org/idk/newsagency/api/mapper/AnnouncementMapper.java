package org.idk.newsagency.api.mapper;

import lombok.extern.java.Log;
import org.idk.newsagency.Utils;
import org.idk.newsagency.annotation.Mapper;
import org.idk.newsagency.api.controller.dto.request.AnnouncementDto;
import org.idk.newsagency.api.controller.dto.response.AnnouncementDtoResponse;
import org.idk.newsagency.entity.Announcement;
import org.idk.newsagency.entity.Location;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.Base64;

@Log
@Mapper
public class AnnouncementMapper extends AbstractMapper<Announcement, AnnouncementDto> {

    public AnnouncementMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public Announcement toEntity(AnnouncementDto dto) {
        Announcement announcement = mapper.map(dto, Announcement.class);

        try {
            String image = Base64.getEncoder().encodeToString(dto.getImage().getBytes());
            announcement.setImage(image);
        } catch (IOException | NullPointerException e) {
            log.warning(e.getMessage());
        }

        Location location = new Location(dto.getLatitude(), dto.getLongitude());
        announcement.setLocation(location);
        announcement.setUser(Utils.getAuthenticatedUser());
        return announcement;
    }

    @Override
    public AnnouncementDtoResponse toDto(Announcement announcement) {
        AnnouncementDtoResponse response = mapper.map(announcement, AnnouncementDtoResponse.class);
        if(announcement.getImage() != null) {
            response.setImage(Base64.getDecoder().decode(announcement.getImage()));
        }
        return response;
    }
}
