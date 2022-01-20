package org.idk.newsagency.entity;

import lombok.Data;
import org.idk.newsagency.entity.enumeration.Section;
import org.idk.newsagency.entity.enumeration.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
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
    @Column(nullable = false)
    private User user;
}
