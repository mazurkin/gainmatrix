package com.gainmatrix.lib.spring.i18n.timezone.context;

import com.google.common.base.Preconditions;

import java.util.TimeZone;

/**
 * Простой контейнер для статичного хранения информации о временной зоне
 * @see org.springframework.context.i18n.SimpleLocaleContext
 */
public class SimpleTimezoneContext implements TimezoneContext {

    private final TimeZone timezone;

    public SimpleTimezoneContext(TimeZone timezone) {
        Preconditions.checkNotNull(timezone, "Timezone must not be null");

        this.timezone = timezone;
    }

    @Override
    public TimeZone getTimezone() {
        return timezone;
    }

    @Override
    public String toString() {
        return this.timezone.toString();
    }

}
