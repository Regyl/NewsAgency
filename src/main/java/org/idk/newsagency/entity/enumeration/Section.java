package org.idk.newsagency.entity.enumeration;

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
}
