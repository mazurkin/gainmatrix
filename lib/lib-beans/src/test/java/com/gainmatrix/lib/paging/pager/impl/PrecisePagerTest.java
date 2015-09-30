package com.gainmatrix.lib.paging.pager.impl;

import org.junit.Assert;
import org.junit.Test;

public class PrecisePagerTest {

@Test
    public void testAll() {
        PrecisePager paginator = new PrecisePager();

        paginator.setPageSize(10);
        paginator.setTotalSize(123);

        //

        Assert.assertEquals(123, paginator.getTotalSize());
        Assert.assertEquals(10, paginator.getPageSize());
        Assert.assertEquals(13, paginator.getPageCount());
        Assert.assertEquals(1, paginator.getPage());

        //

        paginator.setPage(0);
        Assert.assertEquals(1, paginator.getPage());

        paginator.setPage(14);
        Assert.assertEquals(13, paginator.getPage());

        //

        paginator.setPage(8);
        Assert.assertTrue(paginator.isBackAllowed());
        Assert.assertTrue(paginator.isNextAllowed());

        paginator.setTotalSize(68);
        Assert.assertEquals(7, paginator.getPageCount());
        Assert.assertEquals(68, paginator.getTotalSize());
        Assert.assertEquals(7, paginator.getPage());

        paginator.setTotalSize(0);
        Assert.assertEquals(0, paginator.getPageCount());
        Assert.assertEquals(0, paginator.getTotalSize());
        Assert.assertEquals(1, paginator.getPage());
    }

}
