package org.idk.newsagency.service;

import org.idk.newsagency.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {

    private final AnnouncementRepository repository;

    public AnnouncementService(AnnouncementRepository repository) {
        this.repository = repository;
    }
}
