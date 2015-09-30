package com.gainmatrix.lib.spring.i18n.timezone.context;

import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.core.NamedThreadLocal;

import java.util.TimeZone;

/**
 * Контейнер для хранения информации о текущей таймзоне клиента в контексте потока
 * @see org.springframework.context.i18n.LocaleContextHolder
 */
public final class TimezoneContextHolder {

    private static final ThreadLocal<TimezoneContext> TIMEZONE_CONTEXT_HOLDER =
            new NamedThreadLocal<TimezoneContext>("Timezone context");

    private static final ThreadLocal<TimezoneContext> INHERITABLE_TIMEZONE_CONTEXT_HOLDER =
            new NamedInheritableThreadLocal<TimezoneContext>("Timezone context");

    private TimezoneContextHolder() {
    }

    public static void resetTimezoneContext() {
        TIMEZONE_CONTEXT_HOLDER.set(null);
        INHERITABLE_TIMEZONE_CONTEXT_HOLDER.set(null);
    }

    public static void setTimezoneContext(TimezoneContext timezoneContext) {
        setTimezoneContext(timezoneContext, false);
    }

    public static void setTimezoneContext(TimezoneContext timezoneContext, boolean inheritable) {
        if (inheritable) {
            INHERITABLE_TIMEZONE_CONTEXT_HOLDER.set(timezoneContext);
            TIMEZONE_CONTEXT_HOLDER.set(null);
        } else {
            TIMEZONE_CONTEXT_HOLDER.set(timezoneContext);
            INHERITABLE_TIMEZONE_CONTEXT_HOLDER.set(null);
        }
    }

    public static TimezoneContext getTimezoneContext() {
        TimezoneContext localeContext = TIMEZONE_CONTEXT_HOLDER.get();
        if (localeContext == null) {
            localeContext = INHERITABLE_TIMEZONE_CONTEXT_HOLDER.get();
        }
        return localeContext;
    }

    public static void setTimezone(TimeZone timezone) {
        setTimezone(timezone, false);
    }

    public static void setTimezone(TimeZone timezone, boolean inheritable) {
        TimezoneContext timezoneContext = (timezone != null) ? new SimpleTimezoneContext(timezone) : null;
        setTimezoneContext(timezoneContext, inheritable);
    }

    public static TimeZone getTimezone() {
        TimezoneContext timezoneContext = getTimezoneContext();
        return (timezoneContext != null) ? timezoneContext.getTimezone() : TimeZone.getDefault();
    }

}
