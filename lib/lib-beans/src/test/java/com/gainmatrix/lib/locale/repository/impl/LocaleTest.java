package com.gainmatrix.lib.locale.repository.impl;

import com.gainmatrix.lib.time.ChronometerUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class LocaleTest {

    @Test
    public void testSample1() throws Exception {
        Locale locale = new Locale("uk", "TW");

        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String text = format.format(ChronometerUtils.parseMoment("2011-04-26 12:23:23.000 UTC"));
        Assert.assertNotNull(text);
        Assert.assertEquals("вівторок, 26 квітня 2011 р. 12:23:23 UTC", text);
    }

    @Test
    public void testSample2() throws Exception {
        Locale locale = new Locale("ru", "RU");

        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String text = format.format(ChronometerUtils.parseMoment("2011-04-26 12:23:23.000 UTC"));
        Assert.assertNotNull(text);
        Assert.assertEquals("26 Апрель 2011 г. 12:23:23 UTC", text);
    }

    @Test
    public void testSample3() throws Exception {
        Locale locale = new Locale("fr", "RU");

        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String text = format.format(ChronometerUtils.parseMoment("2011-04-26 12:23:23.000 UTC"));
        Assert.assertNotNull(text);
        Assert.assertEquals("mardi 26 avril 2011 12 h 23 UTC", text);
    }

    @Test
    public void testSample4() throws Exception {
        Locale locale = new Locale("en", "GB");

        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String text = format.format(ChronometerUtils.parseMoment("2011-04-26 12:23:23.000 UTC"));
        Assert.assertNotNull(text);
        Assert.assertEquals("Tuesday, 26 April 2011 12:23:23 o'clock UTC", text);
    }

    @Test
    public void testSample5() throws Exception {
        Locale locale = new Locale("en", "US");

        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String text = format.format(ChronometerUtils.parseMoment("2011-04-26 12:23:23.000 UTC"));
        Assert.assertNotNull(text);
        Assert.assertEquals("Tuesday, April 26, 2011 12:23:23 PM UTC", text);
    }

}
