package com.deepspace.newsagency.entity.enumeration;

import lombok.Getter;

import java.util.List;
import java.util.Random;
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

    public static Section random() {
        List<Section> sections = toStream().toList();
        return sections.get(
                new Random().nextInt(sections.size()));
    }
}
