package com.gainmatrix.lib.time;

import com.google.common.base.Preconditions;

/**
 * Измерение интервалов времени
 */
public class ChronometerTimer {

    private final Chronometer chronometer;

    private final long started;

    public ChronometerTimer(Chronometer chronometer) {
        Preconditions.checkNotNull(chronometer, "Chronometer is null");

        this.chronometer = chronometer;
        this.started = chronometer.getCurrentTick();
    }

    /**
     * Запрос прошедшего времени с момента последнего запуска
     * @return Прошедшее время в миллисекундах
     */
    public long elapsed() {
        return chronometer.getCurrentTick() - started;
    }

}
