package ru.oop.journal.model;

import ru.oop.journal.exception.InvalidPointsException;

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

    /**
     * Возвращает минимальное количество баллов для оценки.
     *
     * @return минимальное количество баллов
     */
    public int getMinPoints() {
        return minPoints;
    }

    /**
     * Возвращает максимальное количество баллов для оценки.
     *
     * @return максимальное количество баллов
     */
    public int getMaxPoints() {
        return maxPoints;
    }

    /**
     * Возвращает название оценки.
     *
     * @return описание оценки
     */
    public String getDescription() {
        return description;
    }

    /**
     * Определяет оценку по количеству баллов.
     *
     * @param points количество баллов от 0 до 100
     * @return соответствующая оценка
     * @throws ru.oop.journal.exception.InvalidPointsException
     *         если количество баллов меньше 0 или больше 100
     */
    public static Grade fromPoints(int points) {
        if (points < 0 || points > 100) {
            throw new InvalidPointsException(points);
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

