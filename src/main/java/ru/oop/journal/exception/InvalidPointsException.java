package ru.oop.journal.exception;

public class InvalidPointsException extends IllegalArgumentException {

    public InvalidPointsException(int points) {
        super(
                "Недопустимое количество баллов: "
                        + points
                        + ". Допустимый диапазон: от 0 до 100."
        );
    }
}
