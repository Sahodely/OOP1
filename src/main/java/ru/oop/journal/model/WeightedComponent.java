package ru.oop.journal.model;

/**
 * Компонент, который учитывает вес контрольной точки.
 */
public interface WeightedComponent {

    /**
     * Рассчитывает количество баллов с учётом веса.
     *
     * @param points исходное количество баллов
     * @return взвешенное количество баллов
     */
    double calculateWeightedPoints(int points);

    /**
     * Возвращает тип контрольной точки.
     *
     * @return тип контрольной точки
     */
    String getType();
}

