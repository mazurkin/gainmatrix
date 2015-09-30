package com.gainmatrix.lib.web.resolver.timezone.impl;

import com.gainmatrix.lib.web.resolver.timezone.TimezoneResolver;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.TimeZone;

/**
 * Фиксированная временная зона
 */
public class FixedTimezoneResolver implements TimezoneResolver {

    private TimeZone defaultTimeZone;

    @Override
    public TimeZone resolveTimezone(HttpServletRequest request) {
        if (defaultTimeZone != null) {
            return defaultTimeZone;
        } else {
            return TimeZone.getDefault();
        }
    }

    @Override
    public void setTimezone(HttpServletRequest request, HttpServletResponse response, TimeZone timezone) {
        throw new UnsupportedOperationException("Impossible to set timezone for this resolver");
    }

    @Required
    public void setDefaultTimeZone(TimeZone defaultTimeZone) {
        this.defaultTimeZone = defaultTimeZone;
    }

}
