package ru.oop.journal.model;

import java.util.Objects;

public final class Student {

    private final GradeBookNumber gradeBookNumber;
    private final String fullName;
    private final Group group;

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

    public GradeBookNumber getGradeBookNumber() {
        return gradeBookNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public Group getGroup() {
        return group;
    }

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

    @Override
    public int hashCode() {
        return gradeBookNumber.hashCode();
    }

    @Override
    public String toString() {
        return fullName + " (" + gradeBookNumber.value()
                + "), группа: " + group.name();
    }
}

