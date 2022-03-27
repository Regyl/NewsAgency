package com.deepspace.newsagency.service;

import com.deepspace.newsagency.entity.Comment;
import com.deepspace.newsagency.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record CommentService(CommentRepository repository) {

    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    public List<Comment> findAllByAnnouncementId(UUID id) {
        return repository.findAllByAnnouncement_Id(id);
    }
}
