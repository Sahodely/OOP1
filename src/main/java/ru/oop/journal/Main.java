
package ru.oop.journal;

import ru.oop.journal.model.*;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== ЖУРНАЛ УСПЕВАЕМОСТИ ===");

        Group group = new Group("ИВТ-21");
        Discipline discipline =
                new Discipline("Программирование");

        GradeBookNumber number =
                new GradeBookNumber("12345");

        Student student = new Student(
                number,
                "Иванов Иван",
                group
        );

        System.out.println("\n--- Студент ---");
        System.out.println(student);

        System.out.println("\n--- Оценка ---");
        Grade grade = Grade.fromPoints(95);
        System.out.println(grade.getDescription());

        RegularControlPoint regular =
                new RegularControlPoint(
                        "Контрольная работа №1",
                        discipline,
                        LocalDate.of(2026, 10, 10),
                        0.3
                );

        ExamControlPoint exam =
                new ExamControlPoint(
                        "Итоговый экзамен",
                        discipline,
                        LocalDate.of(2026, 10, 20),
                        0.7
                );

        List<ControlPoint> controlPoints =
                List.of(regular, exam);

        System.out.println("\n--- Полиморфизм ---");

        for (ControlPoint point : controlPoints) {

            System.out.println(
                    "Тип: " + point.getType()
            );

            System.out.println(
                    "Название: " + point.getName()
            );

            System.out.println(
                    "Дата: " + point.getDate()
            );

            System.out.println(
                    "Вес: " + point.getWeight()
            );

            System.out.println(
                    "Взвешенные баллы: "
                            + point.calculateWeightedPoints(80)
            );

            System.out.println();
        }
    }
}
