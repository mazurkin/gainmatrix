package com.gainmatrix.lib.web.attribute.render;

import com.gainmatrix.lib.serialization.SerialVersionUID;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Basic context for rendering web page
 */
public class RenderContext implements Serializable {

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    private String applicationVersion;

    private Locale locale;

    private TimeZone timezone;

    private Date now;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public TimeZone getTimezone() {
        return timezone;
    }

    public void setTimezone(TimeZone timezone) {
        this.timezone = timezone;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

}
