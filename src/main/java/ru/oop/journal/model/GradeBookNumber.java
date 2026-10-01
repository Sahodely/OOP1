package ru.oop.journal.model;

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

