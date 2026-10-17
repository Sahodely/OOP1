package ru.oop.journal.model;

import java.util.Objects;

/**
 * Запись журнала успеваемости.
 * Хранит студента, контрольную точку и количество полученных баллов.
 *
 * @param student студент
 * @param controlPoint контрольная точка
 * @param points количество баллов
 */
public record GradeEntry(
        Student student,
        ControlPoint controlPoint,
        int points
) {

    /**
     * Создаёт запись журнала.
     *
     * @param student студент
     * @param controlPoint контрольная точка
     * @param points количество баллов
     */
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

    /**
     * Возвращает оценку по количеству баллов.
     *
     * @return оценка студента
     */
    public Grade getGrade() {
        return Grade.fromPoints(points);
    }

    /**
     * Возвращает количество баллов с учётом веса контрольной точки.
     *
     * @return взвешенные баллы
     */
    public double getWeightedPoints() {
        return controlPoint.calculateWeightedPoints(points);
    }
}