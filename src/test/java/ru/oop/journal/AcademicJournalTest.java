package ru.oop.journal;

import org.junit.jupiter.api.Test;
import ru.oop.journal.exception.InvalidPointsException;
import ru.oop.journal.model.*;
import ru.oop.journal.service.AcademicJournal;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AcademicJournalTest {

    @Test
    void zeroPointsIsUnsatisfactory() {
        assertEquals(
                Grade.UNSATISFACTORY,
                Grade.fromPoints(0)
        );
    }

    @Test
    void fortyNinePointsIsUnsatisfactory() {
        assertEquals(
                Grade.UNSATISFACTORY,
                Grade.fromPoints(49)
        );
    }

    @Test
    void fiftyPointsIsSatisfactory() {
        assertEquals(
                Grade.SATISFACTORY,
                Grade.fromPoints(50)
        );
    }

    @Test
    void sixtyNinePointsIsSatisfactory() {
        assertEquals(
                Grade.SATISFACTORY,
                Grade.fromPoints(69)
        );
    }

    @Test
    void seventyPointsIsGood() {
        assertEquals(
                Grade.GOOD,
                Grade.fromPoints(70)
        );
    }

    @Test
    void eightyNinePointsIsGood() {
        assertEquals(
                Grade.GOOD,
                Grade.fromPoints(89)
        );
    }

    @Test
    void ninetyPointsIsExcellent() {
        assertEquals(
                Grade.EXCELLENT,
                Grade.fromPoints(90)
        );
    }

    @Test
    void oneHundredPointsIsExcellent() {
        assertEquals(
                Grade.EXCELLENT,
                Grade.fromPoints(100)
        );
    }

    @Test
    void negativePointsThrowsException() {
        assertThrows(
                InvalidPointsException.class,
                () -> Grade.fromPoints(-1)
        );
    }

    @Test
    void pointsAboveHundredThrowsException() {
        assertThrows(
                InvalidPointsException.class,
                () -> Grade.fromPoints(101)
        );
    }

    @Test
    void studentsWithSameGradeBookNumberAreEqual() {

        Group group = new Group("ИВТ-21");

        Student first = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        Student second = new Student(
                new GradeBookNumber("1001"),
                "Другой студент",
                group
        );

        assertEquals(first, second);
    }

    @Test
    void studentsWithDifferentGradeBookNumbersAreNotEqual() {

        Group group = new Group("ИВТ-21");

        Student first = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        Student second = new Student(
                new GradeBookNumber("1002"),
                "Петров Пётр",
                group
        );

        assertNotEquals(first, second);
    }

    @Test
    void equalStudentsHaveSameHashCode() {

        Group group = new Group("ИВТ-21");

        Student first = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        Student second = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        assertEquals(
                first.hashCode(),
                second.hashCode()
        );
    }

    @Test
    void calculatesAverageByStudent() {

        AcademicJournal journal = new AcademicJournal();

        Group group = new Group("ИВТ-21");
        Discipline discipline =
                new Discipline("Программирование");

        Student student = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        ControlPoint control = new RegularControlPoint(
                "Контрольная",
                discipline,
                LocalDate.of(2026, 10, 15),
                0.3
        );

        ControlPoint exam = new ExamControlPoint(
                "Экзамен",
                discipline,
                LocalDate.of(2026, 10, 20),
                0.7
        );

        journal.assignPoints(student, control, 80);
        journal.assignPoints(student, exam, 90);

        assertEquals(
                87.0,
                journal.getAverageByStudent(student),
                0.001
        );
    }

    @Test
    void calculatesAverageByDiscipline() {

        AcademicJournal journal = new AcademicJournal();

        Group group = new Group("ИВТ-21");
        Discipline discipline =
                new Discipline("Программирование");

        Student first = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        Student second = new Student(
                new GradeBookNumber("1002"),
                "Петров Пётр",
                group
        );

        ControlPoint control = new RegularControlPoint(
                "Контрольная",
                discipline,
                LocalDate.of(2026, 10, 15),
                0.3
        );

        ControlPoint exam = new ExamControlPoint(
                "Экзамен",
                discipline,
                LocalDate.of(2026, 10, 20),
                0.7
        );

        journal.assignPoints(first, control, 80);
        journal.assignPoints(first, exam, 90);

        journal.assignPoints(second, control, 60);
        journal.assignPoints(second, exam, 70);

        assertEquals(
                77.0,
                journal.getAverageByDiscipline(discipline),
                0.001
        );
    }

    @Test
    void calculatesAverageByGroup() {

        AcademicJournal journal = new AcademicJournal();

        Group group = new Group("ИВТ-21");
        Discipline discipline =
                new Discipline("Математика");

        Student first = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        Student second = new Student(
                new GradeBookNumber("1002"),
                "Петров Пётр",
                group
        );

        ControlPoint control = new RegularControlPoint(
                "Контрольная",
                discipline,
                LocalDate.of(2026, 10, 15),
                0.5
        );

        journal.assignPoints(first, control, 80);
        journal.assignPoints(second, control, 60);

        assertEquals(
                70.0,
                journal.getAverageByGroup(group),
                0.001
        );
    }

    @Test
    void studentWithoutGradesHasZeroAverage() {

        AcademicJournal journal =
                new AcademicJournal();

        Student student = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                new Group("ИВТ-21")
        );

        assertEquals(
                0.0,
                journal.getAverageByStudent(student),
                0.001
        );
    }

    @Test
    void findsExcellentStudents() {

        AcademicJournal journal =
                new AcademicJournal();

        Group group = new Group("ИВТ-21");
        Discipline discipline =
                new Discipline("Программирование");

        Student excellent = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        Student other = new Student(
                new GradeBookNumber("1002"),
                "Петров Пётр",
                group
        );

        ControlPoint control = new RegularControlPoint(
                "Контрольная",
                discipline,
                LocalDate.of(2026, 10, 15),
                0.3
        );

        ControlPoint exam = new ExamControlPoint(
                "Экзамен",
                discipline,
                LocalDate.of(2026, 10, 20),
                0.7
        );

        journal.assignPoints(excellent, control, 95);
        journal.assignPoints(excellent, exam, 100);

        journal.assignPoints(other, control, 95);
        journal.assignPoints(other, exam, 80);

        List<Student> students =
                journal.getExcellentStudents();

        assertEquals(1, students.size());
        assertTrue(students.contains(excellent));
        assertFalse(students.contains(other));
    }

    @Test
    void findsDebtors() {

        AcademicJournal journal =
                new AcademicJournal();

        Group group = new Group("ИВТ-21");
        Discipline discipline =
                new Discipline("Физика");

        Student debtor = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        Student other = new Student(
                new GradeBookNumber("1002"),
                "Петров Пётр",
                group
        );

        ControlPoint control = new RegularControlPoint(
                "Контрольная",
                discipline,
                LocalDate.of(2026, 10, 15),
                1.0
        );

        journal.assignPoints(debtor, control, 49);
        journal.assignPoints(other, control, 50);

        List<Student> debtors =
                journal.getDebtors();

        assertEquals(1, debtors.size());
        assertTrue(debtors.contains(debtor));
        assertFalse(debtors.contains(other));
    }

    @Test
    void createsStatementForDisciplineAndDate() {

        AcademicJournal journal =
                new AcademicJournal();

        Group group = new Group("ИВТ-21");

        Discipline programming =
                new Discipline("Программирование");

        Discipline mathematics =
                new Discipline("Математика");

        Student student = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        LocalDate date =
                LocalDate.of(2026, 10, 20);

        ControlPoint programmingExam =
                new ExamControlPoint(
                        "Экзамен по программированию",
                        programming,
                        date,
                        1.0
                );

        ControlPoint mathematicsExam =
                new ExamControlPoint(
                        "Экзамен по математике",
                        mathematics,
                        date,
                        1.0
                );

        journal.assignPoints(
                student,
                programmingExam,
                90
        );

        journal.assignPoints(
                student,
                mathematicsExam,
                80
        );

        List<GradeEntry> statement =
                journal.getStatement(
                        programming,
                        date
                );

        assertEquals(1, statement.size());
        assertEquals(
                student,
                statement.get(0).student()
        );
        assertEquals(
                90,
                statement.get(0).points()
        );
    }

    @Test
    void assigningAgainReplacesPreviousPoints() {

        AcademicJournal journal =
                new AcademicJournal();

        Group group =
                new Group("ИВТ-21");

        Discipline discipline =
                new Discipline("Программирование");

        Student student = new Student(
                new GradeBookNumber("1001"),
                "Иванов Иван",
                group
        );

        ControlPoint control =
                new RegularControlPoint(
                        "Контрольная",
                        discipline,
                        LocalDate.of(2026, 10, 15),
                        1.0
                );

        journal.assignPoints(
                student,
                control,
                40
        );

        journal.assignPoints(
                student,
                control,
                90
        );

        List<GradeEntry> statement =
                journal.getStatement(
                        discipline,
                        LocalDate.of(2026, 10, 15)
                );

        assertEquals(1, statement.size());

        assertEquals(
                90,
                statement.get(0).points()
        );
    }
}
