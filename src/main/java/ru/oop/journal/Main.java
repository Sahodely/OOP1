package ru.oop.journal;

import ru.oop.journal.model.Discipline;
import ru.oop.journal.model.Grade;
import ru.oop.journal.model.GradeBookNumber;
import ru.oop.journal.model.Group;

public class Main {

    public static void main(String[] args) {

        Group group = new Group("ИВТ-21");

        Discipline discipline =
                new Discipline("Программирование");

        GradeBookNumber number =
                new GradeBookNumber("12345");

        Grade grade = Grade.fromPoints(95);

        System.out.println("Журнал успеваемости");
        System.out.println("Группа: " + group.name());
        System.out.println("Дисциплина: " + discipline.name());
        System.out.println("Зачётная книжка: " + number.value());
        System.out.println("Оценка: " + grade.getDescription());
    }
}
