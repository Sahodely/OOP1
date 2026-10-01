package ru.oop.journal.model;

import java.time.LocalDate;

public final class RegularControlPoint extends ControlPoint {

    public RegularControlPoint(String name, Discipline discipline, LocalDate date, double weight) {
        super(name, discipline, date, weight);
    }

    @Override
    public String getType() {
        return "Текущая контрольная";
    }
}

