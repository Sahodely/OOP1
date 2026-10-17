package ru.oop.journal.model;

import java.util.Objects;

/**
 * Студент учебной группы.
 * Равенство студентов определяется по номеру зачётной книжки.
 */
public final class Student {

    private final GradeBookNumber gradeBookNumber;
    private final String fullName;
    private final Group group;

    /**
     * Создаёт студента.
     *
     * @param gradeBookNumber номер зачётной книжки
     * @param fullName ФИО студента
     * @param group учебная группа
     */
    public Student(GradeBookNumber gradeBookNumber,
                   String fullName,
                   Group group) {

        this.gradeBookNumber = Objects.requireNonNull(
                gradeBookNumber,
                "Номер зачётной книжки обязателен"
        );

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException(
                    "ФИО студента не может быть пустым"
            );
        }

        this.fullName = fullName.trim();

        this.group = Objects.requireNonNull(
                group,
                "Группа обязательна"
        );
    }

    /**
     * Возвращает номер зачётной книжки студента.
     *
     * @return номер зачётной книжки
     */
    public GradeBookNumber getGradeBookNumber() {
        return gradeBookNumber;
    }

    /**
     * Возвращает ФИО студента.
     *
     * @return ФИО студента
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Возвращает учебную группу студента.
     *
     * @return учебная группа
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Сравнивает студентов по номеру зачётной книжки.
     *
     * @param obj объект для сравнения
     * @return true, если номера зачётных книжек совпадают
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Student other)) {
            return false;
        }

        return gradeBookNumber.equals(other.gradeBookNumber);
    }

    /**
     * Возвращает хеш-код на основе номера зачётной книжки.
     *
     * @return хеш-код студента
     */
    @Override
    public int hashCode() {
        return gradeBookNumber.hashCode();
    }

    /**
     * Возвращает строковое представление студента.
     *
     * @return информация о студенте
     */
    @Override
    public String toString() {
        return fullName + " (" + gradeBookNumber.value()
                + "), группа: " + group.name();
    }
}