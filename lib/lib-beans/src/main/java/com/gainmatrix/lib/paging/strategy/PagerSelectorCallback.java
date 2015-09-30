package com.gainmatrix.lib.paging.strategy;

import com.gainmatrix.lib.paging.Extraction;

import java.util.List;

/**
 * Интерфейс обратного вызова для стратегии пейджинга
 * @param <T> Тип сущности
 */
public interface PagerSelectorCallback<T> {

    /**
     * Обратный вызов для подгрузки набора данных по указанной выборке
     * @param extraction Ограничение выборки
     * @return Результат подгрузки в виде списка объектов
     */
    List<T> select(Extraction extraction);

}
