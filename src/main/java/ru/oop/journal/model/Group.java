package ru.oop.journal.model;

/**
 * Учебная группа студентов.
 *
 * @param name название группы
 */
public record Group(String name) {

    public Group {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Название группы не может быть пустым"
            );
        }

        name = name.trim();
    }
}
