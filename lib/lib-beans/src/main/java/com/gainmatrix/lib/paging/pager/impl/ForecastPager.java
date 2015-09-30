package com.gainmatrix.lib.paging.pager.impl;

import com.gainmatrix.lib.paging.Extraction;
import com.google.common.base.Preconditions;

import java.util.List;

/**
 * Класс для навигации для случаев когда общее число элементов неизвестно
 */
public class ForecastPager extends AbstractPager {

    public static final int DEFAULT_PAGE_LIMIT = 1000;

    public static final int DEFAULT_FORECAST = 1;

    private int pageLimit;

    private int forecast;

    public ForecastPager() {
        this.pageLimit = DEFAULT_PAGE_LIMIT;
        this.forecast = DEFAULT_FORECAST;
    }

    public int getForecast() {
        return forecast;
    }

    public void setForecast(int forecast) {
        Preconditions.checkArgument(forecast > 0, "Forecast count must be positive");

        this.forecast = forecast;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        Preconditions.checkArgument(pageLimit >= 0, "Pages limit must be positive or zero");

        this.pageLimit = pageLimit;
    }

    @Override
    public Extraction getExtraction() {
        int offset = (getPage() - 1) * getPageSize();
        int count = forecast * getPageSize() + 1;

        return new Extraction(offset, count);
    }

    @Override
    public void setPage(int page) {
        if ((pageLimit > 0) && (page > pageLimit)) {
            super.setPage(pageLimit);
        } else {
            super.setPage(page);
        }
    }

    @Override
    public <T> List<T> analyse(List<T> items) {
        Preconditions.checkNotNull(items, "Result list is null");

        int resultSize = items.size();
        int resultPages = (resultSize + getPageSize() - 1) / getPageSize();

        if (resultSize > getPageSize()) {
            setPageCount(getPage() + resultPages - 1);
        } else {
            setPageCount(getPage());
        }

        if ((pageLimit > 0) && (getPageCount() > pageLimit)) {
            setPageCount(pageLimit);
        }

        if (resultSize > getPageSize()) {
            return items.subList(0, getPageSize());
        } else {
            return items;
        }
    }

}
