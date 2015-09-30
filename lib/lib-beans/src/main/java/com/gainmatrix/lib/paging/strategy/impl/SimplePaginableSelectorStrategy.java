package com.gainmatrix.lib.paging.strategy.impl;

import com.gainmatrix.lib.paging.pager.Pager;
import com.gainmatrix.lib.paging.strategy.PagerSelectorCallback;

import java.util.List;

public class SimplePaginableSelectorStrategy extends AbstractComplexPaginableSelectorStrategy {

    @Override
    public <T> List<T> selectItems(Pager pager, PagerSelectorCallback<T> selector) {
        return selector.select(pager.getExtraction());
    }

}
