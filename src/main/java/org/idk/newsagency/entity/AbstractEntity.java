package org.idk.newsagency.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private UUID id;
}
