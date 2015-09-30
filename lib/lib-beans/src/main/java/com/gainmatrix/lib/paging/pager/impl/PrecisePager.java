package com.gainmatrix.lib.paging.pager.impl;

import com.gainmatrix.lib.paging.Extraction;
import com.google.common.base.Preconditions;

import java.util.List;

/**
 * Класс инкапсулирующий состояние и логику перехода по страницам в случае когда известно
 * общее число элементов
 */
public class PrecisePager extends AbstractPager {

    private int totalSize;

    public PrecisePager() {
        this.totalSize = 0;
    }

    @Override
    public Extraction getExtraction() {
        int offset = (getPage() - 1) * getPageSize();

        return new Extraction(offset, getPageSize());
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        Preconditions.checkArgument(totalSize >= 0, "Total size is negative");

        this.totalSize = totalSize;

        recalculate();
    }

    @Override
    public void setPageSize(int pageSize) {
        super.setPageSize(pageSize);

        recalculate();
    }

    @Override
    public void setPage(int page) {
        if (page > getPageCount()) {
            super.setPage(getPageCount());
        } else {
            super.setPage(page);
        }
    }

    @Override
    public <T> List<T> analyse(List<T> items) {
        Preconditions.checkNotNull(items, "Result list is null");

        // Если получена неполная страница - то впереди больше страниц не будет
        if (items.size() < getPageSize()) {
            setPageCount(getPage());
        }

        return items;
    }

    private void recalculate() {
        setPageCount((totalSize + getPageSize() - 1) / getPageSize());

        if (getPage() > getPageCount()) {
            setPage(getPageCount());
        }

        if (getPage() < 1) {
            setPage(1);
        }
    }

}
