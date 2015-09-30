package com.gainmatrix.lib.time;

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class ChronometerUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS z";

    public static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");

    private ChronometerUtils() {
    }

    public static Date parseMoment(String moment) {
        Preconditions.checkNotNull(moment, "Moment is null");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_PATTERN, Locale.getDefault());
        simpleDateFormat.setTimeZone(DEFAULT_TIMEZONE);
        simpleDateFormat.setLenient(false);

        try {
            return simpleDateFormat.parse(moment);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Wrong date format for string (" + moment + ")", e);
        }
    }

}
