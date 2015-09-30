package com.gainmatrix.lib.spring.i18n;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Доступ к локализационным настройкам текущего запроса - к локали и временной зоне
 */
public interface ClientI18nResolver {

    /**
     * Запрос локали текущего запроса
     * @return Локаль
     */
    Locale getLocale();

    /**
     * Запрос временной зоны текущего запроса
     * @return Временная зона
     */
    TimeZone getTimeZone();

}
