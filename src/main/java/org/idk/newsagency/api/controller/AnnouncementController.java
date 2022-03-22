package org.idk.newsagency.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.idk.newsagency.Utils;
import org.idk.newsagency.api.controller.dto.request.AnnouncementDto;
import org.idk.newsagency.api.controller.dto.response.AnnouncementDtoResponse;
import org.idk.newsagency.api.mapper.AnnouncementMapper;
import org.idk.newsagency.entity.Announcement;
import org.idk.newsagency.entity.User;
import org.idk.newsagency.entity.enumeration.Section;
import org.idk.newsagency.entity.enumeration.Status;
import org.idk.newsagency.service.AnnouncementService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public AnnouncementDtoResponse create(@RequestBody @Valid AnnouncementDto dto) {
        Announcement announcement = mapper.toEntity(dto);
        announcement = service.save(announcement);
        return mapper.toDto(announcement);
    }

    @GetMapping("/")
    @Operation(summary = "Return all announcements")
    public List<AnnouncementDtoResponse> findAll() {
        return service.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/pages")
    @Operation(summary = "Pageable")
    public Page<AnnouncementDtoResponse> findByPageable(@ParameterObject @NotNull Pageable pageable) {
        return service.findByPageable(pageable)
                .map(mapper::toDto);
    }

    @PutMapping("/likes/{id}")
    @Operation(summary = "Like announcement")
    public AnnouncementDtoResponse likeAnnouncement(@PathVariable UUID id) {
        Announcement announcement = service.findById(id);
        User user = Utils.getAuthenticatedUser();
        user.getLikedAnnouncements().add(announcement);
        return mapper.toDto(announcement);
    }

    @PutMapping("/moderation")
    @Operation(summary = "Update announcement status")
    public AnnouncementDtoResponse changeAnnotationStatus(@RequestParam UUID id,
                                                          @RequestParam Status status) {
        Announcement announcement = service.findById(id);
        announcement.setStatus(status);
        announcement = service.save(announcement);
        return mapper.toDto(announcement);
    }

    @GetMapping("/sections")
    @Operation(summary = "List of sections")
    public List<Section> getAllSections() {
        return Section.toStream().toList();
    }
}
