package ru.oop.journal.service;

import ru.oop.journal.model.ControlPoint;
import ru.oop.journal.model.Discipline;
import ru.oop.journal.model.Grade;
import ru.oop.journal.model.GradeEntry;
import ru.oop.journal.model.Group;
import ru.oop.journal.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Журнал успеваемости студентов.
 */
public class AcademicJournal {

    private final List<GradeEntry> entries = new ArrayList<>();

    /**
     * Выставляет студенту баллы за контрольную точку.
     * Если баллы уже были выставлены, старое значение заменяется.
     *
     * @param student студент
     * @param controlPoint контрольная точка
     * @param points количество баллов
     */
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

    /**
     * Рассчитывает средний балл студента с учётом весов
     * контрольных точек.
     *
     * @param student студент
     * @return средний балл студента
     */
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

    /**
     * Рассчитывает средний балл по дисциплине.
     *
     * @param discipline дисциплина
     * @return средний балл по дисциплине
     */
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

    /**
     * Рассчитывает средний балл группы.
     *
     * @param group группа
     * @return средний балл группы
     */
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

    /**
     * Возвращает список отличников.
     * Отличником считается студент, у которого все оценки отличные.
     *
     * @return список отличников
     */
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

    /**
     * Возвращает список должников.
     * Должником считается студент, у которого есть хотя бы одна
     * неудовлетворительная оценка.
     *
     * @return список должников
     */
    public List<Student> getDebtors() {

        Set<Student> debtors = new LinkedHashSet<>();

        for (GradeEntry entry : entries) {
            if (entry.getGrade() == Grade.UNSATISFACTORY) {
                debtors.add(entry.student());
            }
        }

        return List.copyOf(debtors);
    }

    /**
     * Формирует ведомость по дисциплине на указанную дату.
     *
     * @param discipline дисциплина
     * @param date дата
     * @return список записей ведомости
     */
    public List<GradeEntry> getStatement(Discipline discipline,
                                         LocalDate date) {

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