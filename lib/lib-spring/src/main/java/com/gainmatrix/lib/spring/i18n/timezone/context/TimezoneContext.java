package com.gainmatrix.lib.spring.i18n.timezone.context;

import java.util.TimeZone;

/**
 * Запрос информации о временной зоне
 * @see org.springframework.context.i18n.LocaleContext
 * @see org.springframework.context.i18n.LocaleContextHolder
 */
public interface TimezoneContext {

    /**
     * Запрос временной зоны, которая может определять статически или динамически
     * @return Временная зона
     */
    TimeZone getTimezone();

}
