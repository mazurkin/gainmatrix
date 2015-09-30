package com.gainmatrix.lib.spring.i18n.impl;

import com.gainmatrix.lib.spring.i18n.ClientI18nResolver;
import com.gainmatrix.lib.spring.i18n.timezone.context.TimezoneContextHolder;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Реализация доступа к локали и временной зоне на основе статических контекстов Spring Framework
 */
public class ClientI18nResolverImpl implements ClientI18nResolver {

    @Override
    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    @Override
    public TimeZone getTimeZone() {
        return TimezoneContextHolder.getTimezone();
    }

}
