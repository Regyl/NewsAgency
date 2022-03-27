package com.deepspace.newsagency.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment extends AbstractEntity {

    @ManyToOne
    private Announcement announcement;

    @ManyToOne
    private User user;

    @NotNull
    @Column(nullable = false)
    private String text;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;
}
