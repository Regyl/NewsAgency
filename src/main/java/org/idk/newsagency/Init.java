package org.idk.newsagency;

import org.idk.newsagency.api.controller.dto.request.UserDto;
import org.idk.newsagency.entity.Announcement;
import org.idk.newsagency.entity.Authority;
import org.idk.newsagency.entity.User;
import org.idk.newsagency.entity.enumeration.Role;
import org.idk.newsagency.entity.enumeration.Section;
import org.idk.newsagency.entity.enumeration.Status;
import org.idk.newsagency.service.AnnouncementService;
import org.idk.newsagency.service.AuthorityService;
import org.idk.newsagency.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public record Init(AuthorityService authorityService, AnnouncementService announcementService,
                   UserService userService) {

    @PostConstruct
    private void init() {

        List<Authority> authorities = authorityService.findAll();
        if (authorities.isEmpty()) {
            for (Role role : Role.values()) {
                Authority authority = new Authority(role);
                authorities.add(authority);
            }
            authorityService.saveAll(authorities);
        }

        User user = new User();


        List<Announcement> announcements = announcementService.findAll();
        if(announcements.isEmpty()) {
            for(int i=0; i<5; i++) {
                Announcement announcement = new Announcement();
                announcement.setHeader("Название" + i);
                announcement.setDescription("Какое-то относительно небольшое описание" + i);
                announcement.setStatus(Status.PUBLISHED);
                announcement.setSection(Section.random());
                announcement.setUser();
            }
        }
    }
}
