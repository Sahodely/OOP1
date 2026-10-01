package ru.oop.journal.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class ControlPoint implements WeightedComponent {

    private final String name;
    private final Discipline discipline;
    private final LocalDate date;
    private final double weight;

    protected ControlPoint(String name, Discipline discipline, LocalDate date, double weight) {
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

    public String getName() {
        return name;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double calculateWeightedPoints(int points) {
        Grade.fromPoints(points);
        return points * weight;
    }

    @Override
    public abstract String getType();
}

