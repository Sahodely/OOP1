package ru.oop.journal.model;

import java.time.LocalDate;

/**
 * Обычная контрольная точка.
 */
public final class RegularControlPoint extends ControlPoint {

    /**
     * Создаёт обычную контрольную точку.
     *
     * @param name название контрольной точки
     * @param discipline дисциплина
     * @param date дата проведения
     * @param weight вес контрольной точки
     */
    public RegularControlPoint(String name,
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
        return "Текущая контрольная";
    }
}

