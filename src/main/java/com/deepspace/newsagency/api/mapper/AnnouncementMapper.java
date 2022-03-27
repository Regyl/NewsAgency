package com.deepspace.newsagency.api.mapper;

import com.deepspace.newsagency.annotation.Mapper;
import com.deepspace.newsagency.api.controller.dto.request.AnnouncementDto;
import com.deepspace.newsagency.api.controller.dto.response.AnnouncementDtoResponse;
import com.deepspace.newsagency.entity.Announcement;
import com.deepspace.newsagency.entity.Location;
import lombok.extern.java.Log;
import com.deepspace.newsagency.Utils;
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

        response.setLikeQuantity(announcement.getLikes().size());
        return response;
    }
}
