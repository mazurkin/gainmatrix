package com.gainmatrix.lib.paging.pager.impl;

import com.gainmatrix.lib.paging.Extraction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ForecastPagerTest {

    @Test
    public void testAll() {
        ForecastPager pager = new ForecastPager();

        pager.setPageSize(10);
        pager.setForecast(2);
        pager.setPageLimit(100);

        Extraction extraction;
        List<String> list;

        //

        Assert.assertEquals(1, pager.getPage());
        Assert.assertEquals(0, pager.getPageCount());

        //

        extraction = pager.getExtraction();
        Assert.assertEquals(0, extraction.getOffset());
        Assert.assertEquals(21, extraction.getCount());

        //

        list = new ArrayList<String>(Collections.nCopies(21, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(1, pager.getPage());
        Assert.assertEquals(3, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(20, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(1, pager.getPage());
        Assert.assertEquals(2, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(16, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(1, pager.getPage());
        Assert.assertEquals(2, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(10, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(1, pager.getPage());
        Assert.assertEquals(1, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(7, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(1, pager.getPage());
        Assert.assertEquals(1, pager.getPageCount());
        Assert.assertEquals(7, list.size());

        list = new ArrayList<String>(Collections.nCopies(0, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(1, pager.getPage());
        Assert.assertEquals(1, pager.getPageCount());
        Assert.assertEquals(0, list.size());

        //

        pager.setPage(0);
        Assert.assertEquals(1, pager.getPage());

        pager.setPage(10000000);
        Assert.assertEquals(100, pager.getPage());

        //

        pager.setPage(12);

        list = new ArrayList<String>(Collections.nCopies(21, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(12, pager.getPage());
        Assert.assertEquals(14, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(20, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(12, pager.getPage());
        Assert.assertEquals(13, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(16, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(12, pager.getPage());
        Assert.assertEquals(13, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(10, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(12, pager.getPage());
        Assert.assertEquals(12, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(7, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(12, pager.getPage());
        Assert.assertEquals(12, pager.getPageCount());
        Assert.assertEquals(7, list.size());

        list = new ArrayList<String>(Collections.nCopies(0, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(12, pager.getPage());
        Assert.assertEquals(12, pager.getPageCount());
        Assert.assertEquals(0, list.size());

        //

        pager.setPage(99);

        list = new ArrayList<String>(Collections.nCopies(21, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(99, pager.getPage());
        Assert.assertEquals(100, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(20, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(99, pager.getPage());
        Assert.assertEquals(100, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(16, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(99, pager.getPage());
        Assert.assertEquals(100, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(10, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(99, pager.getPage());
        Assert.assertEquals(99, pager.getPageCount());
        Assert.assertEquals(10, list.size());

        list = new ArrayList<String>(Collections.nCopies(7, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(99, pager.getPage());
        Assert.assertEquals(99, pager.getPageCount());
        Assert.assertEquals(7, list.size());

        list = new ArrayList<String>(Collections.nCopies(0, "aaa"));
        list = pager.analyse(list);
        Assert.assertEquals(99, pager.getPage());
        Assert.assertEquals(99, pager.getPageCount());
        Assert.assertEquals(0, list.size());
    }

}
