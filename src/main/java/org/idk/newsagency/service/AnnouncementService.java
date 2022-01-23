package org.idk.newsagency.service;

import org.idk.newsagency.entity.Announcement;
import org.idk.newsagency.repository.AnnouncementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {

    private final AnnouncementRepository repository;

    public AnnouncementService(AnnouncementRepository repository) {
        this.repository = repository;
    }

    public Announcement create(Announcement announcement) {
        return repository.save(announcement);
    }

    public Page<Announcement> findByPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
