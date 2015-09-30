package com.gainmatrix.lib.time;

import com.gainmatrix.lib.time.impl.StubChronometer;
import junit.framework.Assert;
import org.junit.Test;

public class ChronometerTimerTest {

    @Test
    public void testTimer() throws Exception {
        StubChronometer chronometer = new StubChronometer();

        ChronometerTimer timer = new ChronometerTimer(chronometer);
        Assert.assertEquals(0, timer.elapsed());

        chronometer.shiftTick(1000);
        Assert.assertEquals(1000, timer.elapsed());

        chronometer.shiftTick(1000);
        Assert.assertEquals(2000, timer.elapsed());
    }
}
