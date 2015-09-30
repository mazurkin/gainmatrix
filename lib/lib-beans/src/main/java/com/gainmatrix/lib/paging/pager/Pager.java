package com.gainmatrix.lib.paging.pager;

import com.gainmatrix.lib.paging.Extraction;

import java.util.List;

/**
 * Интерфейс доступа к информации о разбивке на страницы
 */
public interface Pager {

    /**
     * Получение текущей страницы
     * @return Номер текущей страницы (начинается с единицы)
     */
    int getPage();

    /**
     * Запрос числа доступных страниц
     * @return Число доступных страниц
     */
    int getPageCount();

    /**
     * Переход на произвольную страницу
     * @param page Номер страницы для перехода (начинается с единицы)
     */
    void setPage(int page);

    /**
     * Получение количества элементов на странице
     * @return количество элементов на страницы
     */
    int getPageSize();

    /**
     * Возможен ли переход на страницу назад
     * @return Возможность перехода
     */
    boolean isBackAllowed();

    /**
     * Переход на страницу назад
     */
    void back();

    /**
     * Возможен ли переход на страницу вперед
     * @return Возможность перехода
     */
    boolean isNextAllowed();

    /**
     * Переход на страницу вперед
     */
    void next();

    /**
     * Запрос диапазона выборки для текущей страницы
     * @return Диапазон выборки
     */
    Extraction getExtraction();

    /**
     * Коррекция пейджинга по результатам запроса элементов текущей страницы
     * @param items Полученный список элементов
     * @param <T> Тип возвращаемой записи
     * @return Результатирующий список
     */
    <T> List<T> analyse(List<T> items);

}
