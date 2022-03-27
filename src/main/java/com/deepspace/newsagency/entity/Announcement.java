package com.deepspace.newsagency.entity;

import lombok.Data;
import com.deepspace.newsagency.entity.enumeration.Section;
import com.deepspace.newsagency.entity.enumeration.Status;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"likes", "comments"})
public class Announcement extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Section section;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(nullable = false)
    private String header;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(columnDefinition = "TEXT")
    private String image;

    @NotNull
    @Column(nullable = false)
    private Location location;

    @OneToOne
    @NotNull
    private User user;

    @OneToMany(mappedBy = "announcement")
    private Set<Like> likes;

    @OneToMany(mappedBy = "announcement")
    private Set<Comment> comments;

}
