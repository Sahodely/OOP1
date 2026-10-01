package ru.oop.journal.model;

public record Discipline(String name) {

    public Discipline {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Название дисциплины не может быть пустым"
            );
        }

        name = name.trim();
    }
}

