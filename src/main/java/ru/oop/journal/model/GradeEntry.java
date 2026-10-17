package ru.oop.journal.model;

import java.util.Objects;

public record GradeEntry(
        Student student,
        ControlPoint controlPoint,
        int points
) {

    public GradeEntry {
        Objects.requireNonNull(
                student,
                "Студент обязателен"
        );

        Objects.requireNonNull(
                controlPoint,
                "Контрольная точка обязательна"
        );

        Grade.fromPoints(points);
    }

    public Grade getGrade() {
        return Grade.fromPoints(points);
    }

    public double getWeightedPoints() {
        return controlPoint.calculateWeightedPoints(points);
    }
}
