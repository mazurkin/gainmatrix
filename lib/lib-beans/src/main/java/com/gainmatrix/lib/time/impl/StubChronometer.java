package com.gainmatrix.lib.time.impl;

import com.gainmatrix.lib.time.Chronometer;
import com.gainmatrix.lib.time.ChronometerUtils;
import com.google.common.base.Preconditions;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class StubChronometer implements Chronometer {

    private Date moment;

    private long tick;

    public StubChronometer() {
        now();
    }

    public final void now() {
        this.moment = new Date();
        this.tick = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }

    public void shiftMoment(int calendarField, int value, TimeZone timeZone) {
        Preconditions.checkNotNull(timeZone, "Time zone is null");

        long prevMomentMs = moment.getTime();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.setTime(moment);
        calendar.add(calendarField, value);
        moment = calendar.getTime();

        long currMomentMs = moment.getTime();

        tick = tick + (currMomentMs - prevMomentMs);
    }

    public void shiftTick(long value) {
        this.tick += value;
        this.moment = new Date(moment.getTime() + value);
    }

    public void setCurrentMoment(String moment) {
        Date momentAsDate = ChronometerUtils.parseMoment(moment);
        setCurrentMoment(momentAsDate);
    }

    public void setCurrentMoment(Date date) {
        this.moment = date;
    }

    @Override
    public Date getCurrentMoment() {
        return moment;
    }

    public void setCurrentTick(long tick) {
        this.tick = tick;
    }

    @Override
    public long getCurrentTick() {
        return tick;
    }

}
