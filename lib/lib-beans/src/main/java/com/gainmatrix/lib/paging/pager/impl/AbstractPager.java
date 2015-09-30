package com.gainmatrix.lib.paging.pager.impl;

import com.gainmatrix.lib.paging.pager.Pager;
import com.google.common.base.Preconditions;

import java.io.Serializable;

public abstract class AbstractPager implements Pager, Serializable {

    public static final int DEFAULT_PAGE_SIZE = 10;

    private int page;

    private int pageCount;

    private int pageSize;

    protected AbstractPager() {
        this.page = 1;
        this.pageCount = 0;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        if (page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    @Override
    public int getPageCount() {
        return pageCount;
    }

    protected void setPageCount(int pageCount) {
        Preconditions.checkArgument(pageCount >= 0, "Page count must be positive or zero");

        this.pageCount = pageCount;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        Preconditions.checkArgument(pageSize > 0, "Page size must be negative or zero");

        this.pageSize = pageSize;
    }

    @Override
    public boolean isBackAllowed() {
        return page > 1;
    }

    @Override
    public boolean isNextAllowed() {
        return page < pageCount;
    }

    @Override
    public void back() {
        setPage(getPage() - 1);
    }

    @Override
    public void next() {
        setPage(getPage() + 1);
    }

}
