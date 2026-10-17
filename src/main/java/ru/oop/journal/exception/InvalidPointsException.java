package ru.oop.journal.exception;

/**
 * Исключение для недопустимого количества баллов.
 */
public class InvalidPointsException extends IllegalArgumentException {

    /**
     * Создаёт исключение для неправильного количества баллов.
     *
     * @param points недопустимое количество баллов
     */
    public InvalidPointsException(int points) {
        super(
                "Недопустимое количество баллов: "
                        + points
                        + ". Допустимый диапазон: от 0 до 100."
        );
    }
}