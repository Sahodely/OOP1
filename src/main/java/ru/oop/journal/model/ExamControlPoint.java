package ru.oop.journal.model;

import java.time.LocalDate;

/**
 * Контрольная точка типа экзамен.
 */
public final class ExamControlPoint extends ControlPoint {

    /**
     * Создаёт экзаменационную контрольную точку.
     *
     * @param name название экзамена
     * @param discipline дисциплина
     * @param date дата проведения
     * @param weight вес контрольной точки
     */
    public ExamControlPoint(String name,
                            Discipline discipline,
                            LocalDate date,
                            double weight) {

        super(name, discipline, date, weight);
    }

    /**
     * Возвращает тип контрольной точки.
     *
     * @return тип контрольной точки
     */
    @Override
    public String getType() {
        return "Экзамен";
    }
}
