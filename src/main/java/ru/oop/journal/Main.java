package ru.oop.journal;

import ru.oop.journal.exception.InvalidPointsException;
import ru.oop.journal.model.*;
import ru.oop.journal.service.AcademicJournal;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== ЖУРНАЛ УСПЕВАЕМОСТИ ==========");

        AcademicJournal journal = new AcademicJournal();

        Group group1 = new Group("ИВТ-21");
        Group group2 = new Group("ИВТ-22");

        Discipline programming = new Discipline("Программирование");
        Discipline mathematics = new Discipline("Математика");
        Discipline physics = new Discipline("Физика");

        Student s1 = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group1
        );

        Student s2 = new Student(
                new GradeBookNumber("1002"),
                "Петров Пётр",
                group1
        );

        Student s3 = new Student(
                new GradeBookNumber("1003"),
                "Сидоров Алексей",
                group1
        );

        Student s4 = new Student(
                new GradeBookNumber("1004"),
                "Смирнова Анна",
                group1
        );

        Student s5 = new Student(
                new GradeBookNumber("1005"),
                "Кузнецов Максим",
                group1
        );

        Student s6 = new Student(
                new GradeBookNumber("2001"),
                "Попова Мария",
                group2
        );

        Student s7 = new Student(
                new GradeBookNumber("2002"),
                "Васильев Артём",
                group2
        );

        Student s8 = new Student(
                new GradeBookNumber("2003"),
                "Соколова Елена",
                group2
        );

        Student s9 = new Student(
                new GradeBookNumber("2004"),
                "Михайлов Дмитрий",
                group2
        );

        Student s10 = new Student(
                new GradeBookNumber("2005"),
                "Новикова Ольга",
                group2
        );

        RegularControlPoint programmingTest =
                new RegularControlPoint(
                        "Контрольная по программированию",
                        programming,
                        LocalDate.of(2026, 10, 10),
                        0.3
                );

        ExamControlPoint programmingExam =
                new ExamControlPoint(
                        "Экзамен по программированию",
                        programming,
                        LocalDate.of(2026, 10, 20),
                        0.7
                );

        RegularControlPoint mathematicsTest =
                new RegularControlPoint(
                        "Контрольная по математике",
                        mathematics,
                        LocalDate.of(2026, 10, 12),
                        0.3
                );

        ExamControlPoint mathematicsExam =
                new ExamControlPoint(
                        "Экзамен по математике",
                        mathematics,
                        LocalDate.of(2026, 10, 22),
                        0.7
                );

        RegularControlPoint physicsTest =
                new RegularControlPoint(
                        "Контрольная по физике",
                        physics,
                        LocalDate.of(2026, 10, 14),
                        0.4
                );

        ExamControlPoint physicsExam =
                new ExamControlPoint(
                        "Экзамен по физике",
                        physics,
                        LocalDate.of(2026, 10, 24),
                        0.6
                );

        List<Student> students = List.of(
                s1, s2, s3, s4, s5,
                s6, s7, s8, s9, s10
        );

        System.out.println("\n--- ПОЛИМОРФИЗМ ---");

        List<ControlPoint> controlPoints = List.of(
                programmingTest,
                programmingExam,
                mathematicsTest,
                mathematicsExam,
                physicsTest,
                physicsExam
        );

        for (ControlPoint point : controlPoints) {
            System.out.println(
                    point.getType()
                            + ": "
                            + point.getName()
                            + ", вес = "
                            + point.getWeight()
            );
        }

        System.out.println("\n--- ВЫСТАВЛЕНИЕ БАЛЛОВ ---");

        journal.assignPoints(s1, programmingTest, 95);
        journal.assignPoints(s1, programmingExam, 98);
        journal.assignPoints(s1, mathematicsTest, 92);

        journal.assignPoints(s2, programmingTest, 80);
        journal.assignPoints(s2, programmingExam, 85);
        journal.assignPoints(s2, mathematicsTest, 75);

        journal.assignPoints(s3, programmingTest, 45);
        journal.assignPoints(s3, programmingExam, 55);
        journal.assignPoints(s3, mathematicsTest, 40);

        journal.assignPoints(s4, programmingTest, 96);
        journal.assignPoints(s4, programmingExam, 93);
        journal.assignPoints(s4, mathematicsTest, 97);

        journal.assignPoints(s5, programmingTest, 70);
        journal.assignPoints(s5, programmingExam, 72);
        journal.assignPoints(s5, mathematicsTest, 68);

        journal.assignPoints(s6, programmingTest, 91);
        journal.assignPoints(s6, programmingExam, 94);
        journal.assignPoints(s6, mathematicsTest, 90);

        journal.assignPoints(s7, programmingTest, 65);
        journal.assignPoints(s7, programmingExam, 73);
        journal.assignPoints(s7, mathematicsTest, 62);

        journal.assignPoints(s8, programmingTest, 30);
        journal.assignPoints(s8, programmingExam, 48);
        journal.assignPoints(s8, mathematicsTest, 55);

        journal.assignPoints(s9, programmingTest, 88);
        journal.assignPoints(s9, programmingExam, 84);
        journal.assignPoints(s9, mathematicsTest, 79);

        journal.assignPoints(s10, programmingTest, 100);
        journal.assignPoints(s10, programmingExam, 96);
        journal.assignPoints(s10, mathematicsTest, 95);

        System.out.println("Выставлено 30 результатов.");

        System.out.println("\n--- СРЕДНИЙ БАЛЛ СТУДЕНТА ---");
        System.out.printf(
                "%s: %.2f%n",
                s1.getFullName(),
                journal.getAverageByStudent(s1)
        );

        System.out.println("\n--- СРЕДНИЙ БАЛЛ ПО ДИСЦИПЛИНЕ ---");
        System.out.printf(
                "%s: %.2f%n",
                programming.name(),
                journal.getAverageByDiscipline(programming)
        );

        System.out.println("\n--- СРЕДНИЙ БАЛЛ ГРУППЫ ---");
        System.out.printf(
                "%s: %.2f%n",
                group1.name(),
                journal.getAverageByGroup(group1)
        );

        System.out.println("\n--- ОТЛИЧНИКИ ---");

        for (Student student : journal.getExcellentStudents()) {
            System.out.println(student);
        }

        System.out.println("\n--- ДОЛЖНИКИ ---");

        for (Student student : journal.getDebtors()) {
            System.out.println(student);
        }

        System.out.println(
                "\n--- ВЕДОМОСТЬ ПО ПРОГРАММИРОВАНИЮ НА 20.10.2026 ---"
        );

        List<GradeEntry> statement =
                journal.getStatement(
                        programming,
                        LocalDate.of(2026, 10, 20)
                );

        for (GradeEntry entry : statement) {
            System.out.println(
                    entry.student().getFullName()
                            + " | "
                            + entry.points()
                            + " баллов | "
                            + entry.getGrade().getDescription()
            );
        }

        System.out.println("\n--- ПРОВЕРКА СОБСТВЕННОГО ИСКЛЮЧЕНИЯ ---");

        try {
            journal.assignPoints(
                    s1,
                    physicsExam,
                    150
            );
        } catch (InvalidPointsException e) {
            System.out.println(
                    "Ошибка обработана: " + e.getMessage()
            );
        }

        System.out.println("\n--- СПИСОК СТУДЕНТОВ ---");

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("\n========== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ==========");
    }
}
