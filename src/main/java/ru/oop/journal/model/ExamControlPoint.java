package ru.oop.journal.model;

import java.time.LocalDate;

public final class ExamControlPoint extends ControlPoint {

    public ExamControlPoint(String name,
                            Discipline discipline,
                            LocalDate date,
                            double weight) {

        super(name, discipline, date, weight);
    }

    @Override
    public String getType() {
        return "Экзамен";
    }
}

