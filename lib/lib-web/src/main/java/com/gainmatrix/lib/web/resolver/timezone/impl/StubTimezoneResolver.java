package com.gainmatrix.lib.web.resolver.timezone.impl;

import com.gainmatrix.lib.web.resolver.timezone.TimezoneResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.TimeZone;

public class StubTimezoneResolver implements TimezoneResolver {

    private TimeZone timezone = TimeZone.getDefault();

    @Override
    public TimeZone resolveTimezone(HttpServletRequest request) {
        return timezone;
    }

    @Override
    public void setTimezone(HttpServletRequest request, HttpServletResponse response, TimeZone timezone) {
        this.timezone = timezone;
    }

}
