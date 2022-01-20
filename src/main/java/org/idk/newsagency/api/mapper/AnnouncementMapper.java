package org.idk.newsagency.api.mapper;

import org.idk.newsagency.annotation.Mapper;
import org.idk.newsagency.api.controller.dto.reponse.AnnouncementDtoResponse;
import org.idk.newsagency.api.controller.dto.request.AnnouncementDto;
import org.idk.newsagency.entity.Announcement;
import org.modelmapper.ModelMapper;

@Mapper
public class AnnouncementMapper {

    private final ModelMapper mapper;

    public AnnouncementMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Announcement map(AnnouncementDto dto) {
        return mapper.map(dto, Announcement.class);
    }

    public AnnouncementDtoResponse map(Announcement announcement) {
        return mapper.map(announcement, AnnouncementDtoResponse.class);
    }
}
