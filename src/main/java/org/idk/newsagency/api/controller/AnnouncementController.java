package org.idk.newsagency.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.idk.newsagency.api.controller.dto.response.AnnouncementDtoResponse;
import org.idk.newsagency.api.controller.dto.request.AnnouncementDto;
import org.idk.newsagency.api.mapper.AnnouncementMapper;
import org.idk.newsagency.entity.Announcement;
import org.idk.newsagency.service.AnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Announcements")
@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService service;
    private final AnnouncementMapper mapper;

    public AnnouncementController(AnnouncementService service, AnnouncementMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create announcement")
    public AnnouncementDtoResponse create(AnnouncementDto dto) {
        Announcement announcement = mapper.toEntity(dto);
        announcement = service.create(announcement);
        return mapper.toDto(announcement);
    }

    @GetMapping("/")
    @Operation(summary = "Return announcements by pages")
    public Page<AnnouncementDtoResponse> findByPageable(@RequestBody Pageable pageable) {
        return service.findByPageable(pageable)
                .map(mapper::toDto);
    }
}
