package org.idk.newsagency;

import org.idk.newsagency.entity.Announcement;
import org.idk.newsagency.entity.Authority;
import org.idk.newsagency.entity.Location;
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
import java.util.Set;
import java.util.UUID;

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
            authorities = authorityService.saveAll(authorities);
        }

        String login = "crocodile01@gmail.com";
        User user;
        if(!userService.isExists(login)) {
            user = new User();
            user.setEmailVerified(true);
            user.setAuthorities(Set.copyOf(authorities));
            user.setLogin(login);
            user.setPassword("not-a-password");
            user = userService.save(user);
        } else {
            user = userService.findByLogin(login);
        }


        List<Announcement> announcements = announcementService.findAll();
        if(announcements.isEmpty()) {
            for(int i=0; i<5; i++) {
                Announcement announcement = new Announcement();
                announcement.setHeader("Название " + i);
                announcement.setDescription("Какое-то относительно небольшое описание " + i);
                announcement.setStatus(Status.PUBLISHED);
                announcement.setSection(Section.random());
                announcement.setUser(user);
                announcement.setText(UUID.randomUUID().toString() + " I-don't-know " + UUID.randomUUID().toString());
                announcement.setLocation(new Location(55.874616, 37.513198));
                announcements.add(announcement);
            }
            announcementService.saveAll(announcements);
        }
    }
}
