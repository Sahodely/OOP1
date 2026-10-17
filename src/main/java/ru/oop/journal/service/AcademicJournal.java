package ru.oop.journal.service;

import ru.oop.journal.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AcademicJournal {

    private final List<GradeEntry> entries = new ArrayList<>();

    public void assignPoints(Student student,
                             ControlPoint controlPoint,
                             int points) {

        Grade.fromPoints(points);

        entries.removeIf(entry ->
                entry.student().equals(student)
                        && entry.controlPoint() == controlPoint
        );

        entries.add(
                new GradeEntry(student, controlPoint, points)
        );
    }

    public double getAverageByStudent(Student student) {

        double weightedSum = 0;
        double totalWeight = 0;

        for (GradeEntry entry : entries) {
            if (entry.student().equals(student)) {
                weightedSum += entry.getWeightedPoints();
                totalWeight += entry.controlPoint().getWeight();
            }
        }

        if (totalWeight == 0) {
            return 0;
        }

        return weightedSum / totalWeight;
    }

    public double getAverageByDiscipline(Discipline discipline) {

        double weightedSum = 0;
        double totalWeight = 0;

        for (GradeEntry entry : entries) {
            if (entry.controlPoint()
                    .getDiscipline()
                    .equals(discipline)) {

                weightedSum += entry.getWeightedPoints();
                totalWeight += entry.controlPoint().getWeight();
            }
        }

        if (totalWeight == 0) {
            return 0;
        }

        return weightedSum / totalWeight;
    }

    public double getAverageByGroup(Group group) {

        double weightedSum = 0;
        double totalWeight = 0;

        for (GradeEntry entry : entries) {
            if (entry.student()
                    .getGroup()
                    .equals(group)) {

                weightedSum += entry.getWeightedPoints();
                totalWeight += entry.controlPoint().getWeight();
            }
        }

        if (totalWeight == 0) {
            return 0;
        }

        return weightedSum / totalWeight;
    }

    public List<Student> getExcellentStudents() {

        Set<Student> students = new LinkedHashSet<>();

        for (GradeEntry entry : entries) {
            students.add(entry.student());
        }

        List<Student> excellentStudents = new ArrayList<>();

        for (Student student : students) {

            boolean excellent = true;

            for (GradeEntry entry : entries) {
                if (entry.student().equals(student)
                        && entry.getGrade() != Grade.EXCELLENT) {

                    excellent = false;
                    break;
                }
            }

            if (excellent) {
                excellentStudents.add(student);
            }
        }

        return List.copyOf(excellentStudents);
    }


    public List<Student> getDebtors() {

        Set<Student> debtors = new LinkedHashSet<>();

        for (GradeEntry entry : entries) {
            if (entry.getGrade() == Grade.UNSATISFACTORY) {
                debtors.add(entry.student());
            }
        }

        return List.copyOf(debtors);
    }


    public List<GradeEntry> getStatement(Discipline discipline, LocalDate date) {

        List<GradeEntry> statement = new ArrayList<>();

        for (GradeEntry entry : entries) {
            if (entry.controlPoint()
                    .getDiscipline()
                    .equals(discipline)
                    && entry.controlPoint()
                    .getDate()
                    .equals(date)) {

                statement.add(entry);
            }
        }

        return List.copyOf(statement);
    }

}