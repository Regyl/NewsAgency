package com.deepspace.newsagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "likes")
@EqualsAndHashCode(exclude = {"announcement", "user"}, callSuper = false)
public class Like extends AbstractEntity {

    @ManyToOne
    private Announcement announcement;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;
}
