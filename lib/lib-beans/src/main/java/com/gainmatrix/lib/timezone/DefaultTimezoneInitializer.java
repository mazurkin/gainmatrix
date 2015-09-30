package com.gainmatrix.lib.timezone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import java.util.TimeZone;

/**
 * Установка временной зоны по-умолчанию для всего приложения
 */
public class DefaultTimezoneInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTimezoneInitializer.class);

    private TimeZone timeZone;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        TimeZone currentTimeZone = TimeZone.getDefault();

        if (timeZone != null) {
            LOGGER.info("Default timezone was [{}] and is changed to [{}]", currentTimeZone.getID(), timeZone.getID());
            TimeZone.setDefault(timeZone);
        } else {
            LOGGER.info("Default timezone is [{}]", currentTimeZone.getID());
        }
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

}
