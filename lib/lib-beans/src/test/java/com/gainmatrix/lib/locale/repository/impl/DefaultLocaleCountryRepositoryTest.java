package com.gainmatrix.lib.locale.repository.impl;

import com.gainmatrix.lib.locale.repository.LocaleCountryDefinition;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

public class DefaultLocaleCountryRepositoryTest {

    @Test
    public void testCommon() throws Exception {
        DefaultLocaleCountryRepository repository = new DefaultLocaleCountryRepository();

        Locale displayLocale = new Locale("fr", "FR");

        //

        List<LocaleCountryDefinition> definitions = repository.getCountryDefinitions(displayLocale);
        Assert.assertNotNull(definitions);
        Assert.assertTrue(definitions.size() > 1);

        for (LocaleCountryDefinition definition : definitions) {
            System.out.printf("%s | %s\n", definition.getCode(), definition.getName());
        }

        LocaleCountryDefinition definition = repository.getCountryDefinition("RU", displayLocale);
        Assert.assertEquals("RU", definition.getCode());
        Assert.assertEquals("Russie", definition.getName());
    }

}
