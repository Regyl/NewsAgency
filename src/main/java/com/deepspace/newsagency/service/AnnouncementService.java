package com.deepspace.newsagency.service;

import com.deepspace.newsagency.entity.Announcement;
import com.deepspace.newsagency.exception.EntityNotFoundException;
import com.deepspace.newsagency.repository.AnnouncementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnnouncementService {

    private final AnnouncementRepository repository;

    public AnnouncementService(AnnouncementRepository repository) {
        this.repository = repository;
    }

    public Announcement save(Announcement announcement) {
        return repository.save(announcement);
    }

    public Page<Announcement> findByPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Announcement findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException.supplierOf(id));
    }

    public List<Announcement> findAll() {
        return repository.findAll();
    }

    public void saveAll(List<Announcement> announcements) {
        repository.saveAll(announcements);
    }
}
