package com.gainmatrix.lib.web.resolver.timezone.impl;

import com.gainmatrix.lib.web.resolver.timezone.TimezoneResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.TimeZone;

/**
 * Определитель таймзоны на основе установленной у клиента и управляемой нами куки
 */
public class CookieTimezoneResolver extends CookieGenerator implements TimezoneResolver {

    public static final String TIMEZONE_REQUEST_ATTRIBUTE_NAME = CookieTimezoneResolver.class.getName() + ".TIMEZONE";

    public static final String DEFAULT_COOKIE_NAME = CookieTimezoneResolver.class.getName() + ".TIMEZONE";

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieTimezoneResolver.class);

    private TimezoneResolver backupTimezoneResolver;

    public CookieTimezoneResolver() {
        setCookieName(DEFAULT_COOKIE_NAME);
    }

    @Override
    public TimeZone resolveTimezone(HttpServletRequest request) {
        TimeZone timezone;

        timezone = (TimeZone) request.getAttribute(TIMEZONE_REQUEST_ATTRIBUTE_NAME);

        if (timezone != null) {
            return timezone;
        }

        Cookie cookie = WebUtils.getCookie(request, getCookieName());

        if (cookie != null) {
            String timezoneId = cookie.getValue();

            if (timezoneId != null) {
                timezone = TimeZone.getTimeZone(timezoneId);

                LOGGER.debug("Parsed cookie value [{}] into timezone '{}'", cookie.getValue(), timezone);

                request.setAttribute(TIMEZONE_REQUEST_ATTRIBUTE_NAME, timezone);

                return timezone;
            }
        }

        return backupTimezoneResolver.resolveTimezone(request);
    }

    @Override
    public void setTimezone(HttpServletRequest request, HttpServletResponse response, TimeZone timezone) {
        if (timezone != null) {
            request.setAttribute(TIMEZONE_REQUEST_ATTRIBUTE_NAME, timezone);
            addCookie(response, timezone.getID());
        } else {
            request.setAttribute(TIMEZONE_REQUEST_ATTRIBUTE_NAME, null);
            removeCookie(response);
        }
    }

    @Required
    public void setBackupTimezoneResolver(TimezoneResolver backupTimezoneResolver) {
        this.backupTimezoneResolver = backupTimezoneResolver;
    }

}
