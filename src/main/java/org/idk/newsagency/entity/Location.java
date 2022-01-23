package org.idk.newsagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @NotNull
    @Column(nullable = false)
    private Long latitude;

    @NotNull
    @Column(nullable = false)
    private Long longitude;
}
