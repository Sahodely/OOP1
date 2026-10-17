package ru.oop.journal.model;

/**
 * Номер зачётной книжки студента.
 *
 * @param value номер зачётной книжки
 */
public record GradeBookNumber(String value) {

    public GradeBookNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Номер зачётной книжки не может быть пустым"
            );
        }

        value = value.trim();
    }
}

