package com.gainmatrix.lib.time.impl;

import com.gainmatrix.lib.time.Chronometer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SystemChronometer implements Chronometer {

    @Override
    public Date getCurrentMoment() {
        return new Date();
    }

    @Override
    public long getCurrentTick() {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }

}
