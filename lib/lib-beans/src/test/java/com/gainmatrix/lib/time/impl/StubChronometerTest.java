package com.gainmatrix.lib.time.impl;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class StubChronometerTest {

    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    @Test
    public void testChronometer() throws Exception {
        StubChronometer chronometer = new StubChronometer();

        Date moment1 = chronometer.getCurrentMoment();
        long tick1 = chronometer.getCurrentTick();
        Assert.assertNotNull(moment1);

        chronometer.shiftMoment(Calendar.SECOND, 1, UTC_TIME_ZONE);

        Date moment2 = chronometer.getCurrentMoment();
        long tick2 = chronometer.getCurrentTick();
        Assert.assertNotNull(moment2);
        Assert.assertEquals(1000, tick2 - tick1);
        Assert.assertEquals(1000, moment2.getTime() - moment1.getTime());

        chronometer.shiftTick(-2000);

        Date moment3 = chronometer.getCurrentMoment();
        long tick3 = chronometer.getCurrentTick();
        Assert.assertNotNull(moment3);
        Assert.assertEquals(-2000, tick3 - tick2);
        Assert.assertEquals(-2000, moment3.getTime() - moment2.getTime());
    }
}
