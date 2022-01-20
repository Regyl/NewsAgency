package org.idk.newsagency.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "Member")
public class User extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String login;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String patronymic;

    @OneToMany(orphanRemoval = true)
    private Set<Announcement> announcements;

    @OneToMany
    private Set<Announcement> likedAnnouncements;
}
