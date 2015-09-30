package com.gainmatrix.lib.timezone.repository.impl;

import com.gainmatrix.lib.time.impl.StubChronometer;
import com.gainmatrix.lib.timezone.repository.TimezoneDefinition;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

public class DefaultTimezoneRepositoryTest {

    @Test
    public void testCommon() throws Exception {
        StubChronometer chronometer = new StubChronometer();
        chronometer.setCurrentMoment("2010-01-07 12:45:10.000 UTC");

        DefaultTimezoneRepository repository = new DefaultTimezoneRepository();
        repository.setChronometer(chronometer);

        Locale displayLocale = new Locale("en", "US");

        //

        List<TimezoneDefinition> definitions = repository.getTimezoneDefinitions(displayLocale);
        Assert.assertNotNull(definitions);
        Assert.assertTrue(definitions.size() > 0);

        for (TimezoneDefinition definition : definitions) {
            System.out.printf("%s | %s | %d\n", definition.getCode(), definition.getName(), definition.getOffset());
        }

        TimezoneDefinition definition = repository.getTimezoneDefinition("Europe/Moscow", displayLocale);
        Assert.assertNotNull(definition);
        Assert.assertEquals("Europe/Moscow", definition.getCode());
        Assert.assertEquals(3600 * 3 * 1000, definition.getOffset());
    }

}
