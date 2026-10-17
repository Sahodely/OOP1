package ru.oop.journal.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Базовый класс контрольной точки.
 * Хранит название, дисциплину, дату проведения и вес.
 */
public abstract class ControlPoint implements WeightedComponent {

    private final String name;
    private final Discipline discipline;
    private final LocalDate date;
    private final double weight;

    /**
     * Создаёт контрольную точку.
     *
     * @param name название контрольной точки
     * @param discipline дисциплина
     * @param date дата проведения
     * @param weight вес контрольной точки от 0 до 1
     */
    protected ControlPoint(String name,
                           Discipline discipline,
                           LocalDate date,
                           double weight) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Название контрольной точки не может быть пустым"
            );
        }

        this.name = name.trim();

        this.discipline = Objects.requireNonNull(
                discipline,
                "Дисциплина обязательна"
        );

        this.date = Objects.requireNonNull(
                date,
                "Дата обязательна"
        );

        if (!Double.isFinite(weight)
                || weight <= 0
                || weight > 1) {
            throw new IllegalArgumentException(
                    "Вес должен быть больше 0 и не больше 1"
            );
        }

        this.weight = weight;
    }

    /**
     * Возвращает название контрольной точки.
     *
     * @return название контрольной точки
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает дисциплину контрольной точки.
     *
     * @return дисциплина
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Возвращает дату проведения контрольной точки.
     *
     * @return дата проведения
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Возвращает вес контрольной точки.
     *
     * @return вес контрольной точки
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Рассчитывает количество баллов с учётом веса контрольной точки.
     *
     * @param points количество баллов от 0 до 100
     * @return количество баллов с учётом веса
     */
    @Override
    public double calculateWeightedPoints(int points) {
        Grade.fromPoints(points);
        return points * weight;
    }

    /**
     * Возвращает тип контрольной точки.
     *
     * @return тип контрольной точки
     */
    @Override
    public abstract String getType();
}

