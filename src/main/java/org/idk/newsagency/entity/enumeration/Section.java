package org.idk.newsagency.entity.enumeration;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Section {
    HPU("ЖКХ"),
    EVENTS("События"),
    OFFICIALS("Чиновники"),
    MESS("Беспредел"),
    INCIDENTS("Происшествия"),
    ACTIVITIES("Мероприятия"),
    PROBLEMS("Проблемы"),
    ECOLOGY("Экология");

    private final String translation;

    Section(String translation) {
        this.translation = translation;
    }

    public static Stream<Section> toStream() {
        return Stream.of(Section.values());
    }
}
