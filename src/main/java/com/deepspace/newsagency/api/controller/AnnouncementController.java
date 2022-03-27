package com.deepspace.newsagency.api.controller;

import com.deepspace.newsagency.api.controller.dto.request.AnnouncementDto;
import com.deepspace.newsagency.api.controller.dto.response.AnnouncementDtoResponse;
import com.deepspace.newsagency.api.controller.dto.response.CommentDtoResponse;
import com.deepspace.newsagency.api.controller.dto.response.LikeDtoResponse;
import com.deepspace.newsagency.api.mapper.CommentMapper;
import com.deepspace.newsagency.api.mapper.LikeMapper;
import com.deepspace.newsagency.entity.Announcement;
import com.deepspace.newsagency.entity.Comment;
import com.deepspace.newsagency.entity.Like;
import com.deepspace.newsagency.entity.User;
import com.deepspace.newsagency.entity.enumeration.Section;
import com.deepspace.newsagency.service.CommentService;
import com.deepspace.newsagency.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.deepspace.newsagency.Utils;
import com.deepspace.newsagency.api.mapper.AnnouncementMapper;
import com.deepspace.newsagency.entity.enumeration.Status;
import com.deepspace.newsagency.service.AnnouncementService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Tag(name = "Announcements")
@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService service;
    private final AnnouncementMapper mapper;
    private final LikeService likeService;
    private final LikeMapper likeMapper;
    private final CommentMapper commentMapper;
    private final CommentService commentService;

    public AnnouncementController(AnnouncementService service, AnnouncementMapper mapper,
                                  LikeService likeService, LikeMapper likeMapper,
                                  CommentMapper commentMapper, CommentService commentService) {
        this.service = service;
        this.mapper = mapper;
        this.likeService = likeService;
        this.likeMapper = likeMapper;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @PostMapping(value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create announcement")
    public AnnouncementDtoResponse create(@ModelAttribute AnnouncementDto dto) {
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

    @PostMapping("/likes/{id}")
    @Operation(summary = "Like announcement")
    public void likeAnnouncement(@PathVariable UUID id) {
        Announcement announcement = service.findById(id);
        User user = Utils.getAuthenticatedUser();

        Like like = new Like();
        like.setAnnouncement(announcement);
        like.setUser(user);
        likeService.save(like);
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

    @GetMapping("/likes/{id}")
    @Operation(summary = "Get announcement likes")
    public List<LikeDtoResponse> getLikesById(@PathVariable UUID id) {
        return likeService.findAllByAnnouncementId(id).stream()
                .map(likeMapper::toDto)
                .toList();
    }

    @GetMapping("/comments/{id}")
    @Operation(summary = "Return announcement comments by id")
    public List<CommentDtoResponse> getCommentsById(@PathVariable UUID id) {
        return commentService.findAllByAnnouncementId(id).stream()
                .map(commentMapper::toDto)
                .toList();
    }

    @PostMapping("/comments/{id}")
    @Operation(summary = "Set the comment")
    public void saveComment(@PathVariable UUID id, @RequestParam @NotBlank String text) {
        Announcement announcement = service.findById(id);
        User user = Utils.getAuthenticatedUser();

        Comment comment = new Comment();
        comment.setAnnouncement(announcement);
        comment.setUser(user);
        comment.setText(text);
        commentService.save(comment);
    }
}
