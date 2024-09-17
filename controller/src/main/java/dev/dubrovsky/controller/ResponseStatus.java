package dev.dubrovsky.controller;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    CREATED("Создано!"),
    UPDATED("Обновлено!"),
    DELETED("Удалено!");

    private final String description;

    ResponseStatus(String description) {
        this.description = description;
    }

}
