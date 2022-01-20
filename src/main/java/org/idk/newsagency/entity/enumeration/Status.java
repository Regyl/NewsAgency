package org.idk.newsagency.entity.enumeration;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Status {
    DRAFT("Черновик"),
    MODERATED("На модерации"),
    PUBLISHED("Опубликовано");

    private final String translation;

    Status(String translation) {
        this.translation=translation;
    }
}
