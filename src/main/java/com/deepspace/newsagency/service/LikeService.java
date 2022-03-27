package com.deepspace.newsagency.service;

import com.deepspace.newsagency.entity.Like;
import com.deepspace.newsagency.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record LikeService(LikeRepository repository) {

    public Like save(Like like) {
        return repository.save(like);
    }

    public List<Like> findAllByAnnouncementId(UUID id) {
        return repository.findAllByAnnouncement_Id(id);
    }
}
