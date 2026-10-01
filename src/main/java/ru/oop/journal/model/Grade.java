package ru.oop.journal.model;

public enum Grade {

    UNSATISFACTORY(0, 49, "Неудовлетворительно"),
    SATISFACTORY(50, 69, "Удовлетворительно"),
    GOOD(70, 89, "Хорошо"),
    EXCELLENT(90, 100, "Отлично");

    private final int minPoints;
    private final int maxPoints;
    private final String description;

    Grade(int minPoints, int maxPoints, String description) {
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
        this.description = description;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public String getDescription() {
        return description;
    }

    public static Grade fromPoints(int points) {
        if (points < 0 || points > 100) {
            throw new IllegalArgumentException(
                    "Баллы должны быть от 0 до 100: " + points
            );
        }

        for (Grade grade : values()) {
            if (points >= grade.minPoints
                    && points <= grade.maxPoints) {
                return grade;
            }
        }

        throw new IllegalStateException(
                "Не удалось определить оценку"
        );
    }
}

