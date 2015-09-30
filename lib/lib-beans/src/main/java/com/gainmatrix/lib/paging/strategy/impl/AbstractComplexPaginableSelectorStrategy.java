package com.gainmatrix.lib.paging.strategy.impl;

import com.gainmatrix.lib.paging.pager.Pager;
import com.gainmatrix.lib.paging.strategy.PagerSelectorCallback;
import com.gainmatrix.lib.paging.strategy.PagerSelectorStrategy;
import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;

/**
 * Базовый класс для реализации стратегий выборки
 */
public abstract class AbstractComplexPaginableSelectorStrategy implements PagerSelectorStrategy {

    @Override
    public final <T> List<T> select(Pager pager, PagerSelectorCallback<T> selector) {
        Preconditions.checkNotNull(pager, "Pager is null");
        Preconditions.checkNotNull(selector, "Selector is null");

        // Выборка пуста?
        if (pager.getExtraction().isEmpty()) {
            return Collections.emptyList();
        }

        // Получаем элементы
        List<T> items = selectItems(pager, selector);

        // Анализ результата
        items = pager.analyse(items);

        return items;
    }

    protected abstract <T> List<T> selectItems(Pager pager, PagerSelectorCallback<T> selector);
}
