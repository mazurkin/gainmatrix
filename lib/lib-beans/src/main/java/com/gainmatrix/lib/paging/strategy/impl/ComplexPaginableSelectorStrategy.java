package com.gainmatrix.lib.paging.strategy.impl;

import com.gainmatrix.lib.paging.pager.Pager;
import com.gainmatrix.lib.paging.strategy.PagerSelectorCallback;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class ComplexPaginableSelectorStrategy extends AbstractComplexPaginableSelectorStrategy {

    @Override
    public <T> List<T> selectItems(Pager pager, PagerSelectorCallback<T> selector) {
        // Подгружаем текущую страницу
        List<T> items = selector.select(pager.getExtraction());

        // Если данных на текущей странице нет, пытаемся подгрузить предыдущую страницу
        if (CollectionUtils.isEmpty(items) && (pager.isBackAllowed())) {
            // Подгрузка предыдущей страницы
            pager.back();
            items = selector.select(pager.getExtraction());
            // Если данных на предыдущей странице нет, пытаемся подгрузить первую страницу
            if (CollectionUtils.isEmpty(items) && (pager.isBackAllowed())) {
                // Подгрузка первой страницы
                pager.setPage(1);
                items = selector.select(pager.getExtraction());
            }
        }

        return items;
    }

}
