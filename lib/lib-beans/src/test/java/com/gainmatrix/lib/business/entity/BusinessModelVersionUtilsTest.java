package com.gainmatrix.lib.business.entity;

import junit.framework.Assert;
import org.junit.Test;

public class BusinessModelVersionUtilsTest {

    @Test
    public void testParseBusinessModelVersion() throws Exception {
        int result;

        result = BusinessModelVersionUtils.parseBusinessModelVersion("1.2-SNAPSHOT");
        Assert.assertEquals(10020, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion("1.2.3.4.5-SNAPSHOT");
        Assert.assertEquals(10020, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion("1.2");
        Assert.assertEquals(10021, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion("1.2.3.4.5");
        Assert.assertEquals(10021, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion("123.456");
        Assert.assertEquals(1234561, result);
    }

    @Test(expected = IllegalStateException.class)
    public void testMajorTooHigh() throws Exception {
        BusinessModelVersionUtils.parseBusinessModelVersion("12345.456");
    }

    @Test(expected = IllegalStateException.class)
    public void testMinorTooHigh() throws Exception {
        BusinessModelVersionUtils.parseBusinessModelVersion("1.4567");
    }

    @Test
    public void testUnknownVersion() {
        int result;

        result = BusinessModelVersionUtils.parseBusinessModelVersion("1.2-TEST");
        Assert.assertEquals(BusinessModelVersionUtils.UNKNOWN_VERSION, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion("1.2a");
        Assert.assertEquals(BusinessModelVersionUtils.UNKNOWN_VERSION, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion("1,2");
        Assert.assertEquals(BusinessModelVersionUtils.UNKNOWN_VERSION, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion("1");
        Assert.assertEquals(BusinessModelVersionUtils.UNKNOWN_VERSION, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion("abc");
        Assert.assertEquals(BusinessModelVersionUtils.UNKNOWN_VERSION, result);

        result = BusinessModelVersionUtils.parseBusinessModelVersion((String) null);
        Assert.assertEquals(BusinessModelVersionUtils.UNKNOWN_VERSION, result);
    }

}
