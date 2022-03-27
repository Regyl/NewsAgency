package com.deepspace.newsagency.entity.enumeration;

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
