package com.gainmatrix.lib.time;

import java.util.Date;

/**
 * Time access abstraction. Allow to inject any time dependency when test run
 */
public interface Chronometer {

    /**
     * Получить текущеее время
     * @return Текущее время
     */
    Date getCurrentMoment();

    /**
     * Получить текущий квант времени
     * @return Квант в миллисекундах
     */
    long getCurrentTick();

}
