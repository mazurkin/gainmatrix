package com.gainmatrix.lib.paging.strategy;

import com.gainmatrix.lib.paging.pager.Pager;

import java.util.List;

/**
 * Базовый интерфейс для реализации стратегии выборки данных
 */
public interface PagerSelectorStrategy {

    /**
     * Стратения подгрузки сущностей
     * @param pager Пейджер
     * @param selector Обратный вызов для подгрузки списка объектов
     * @param <T> Тип сущности
     * @return Список сущностей
     */
    <T> List<T> select(Pager pager, PagerSelectorCallback<T> selector);

}
