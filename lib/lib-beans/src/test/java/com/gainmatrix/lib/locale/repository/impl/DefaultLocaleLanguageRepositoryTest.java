package com.gainmatrix.lib.locale.repository.impl;

import com.gainmatrix.lib.locale.repository.LocaleLanguageDefinition;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

public class DefaultLocaleLanguageRepositoryTest {

    @Test
    public void testCommon() throws Exception {
        DefaultLocaleLanguageRepository repository = new DefaultLocaleLanguageRepository();

        Locale displayLocale = new Locale("fr", "FR");

        //

        List<LocaleLanguageDefinition> definitions = repository.getLanguageDefinitions(displayLocale);
        Assert.assertNotNull(definitions);
        Assert.assertTrue(definitions.size() > 1);

        for (LocaleLanguageDefinition definition : definitions) {
            System.out.printf("%s | %s\n", definition.getCode(), definition.getName());
        }

        LocaleLanguageDefinition definition = repository.getLanguageDefinition("ru", displayLocale);
        Assert.assertEquals("ru", definition.getCode());
        Assert.assertEquals("russe", definition.getName());
    }

    @Test
    public void testRestricted() throws Exception {
        DefaultLocaleLanguageRepository repository = new DefaultLocaleLanguageRepository();
        repository.setAllowedCodes(Sets.newHashSet("ru", "en"));

        Locale displayLocale = new Locale("fr", "FR");

        //

        List<LocaleLanguageDefinition> definitions = repository.getLanguageDefinitions(displayLocale);
        Assert.assertNotNull(definitions);
        Assert.assertEquals(2, definitions.size());

        for (LocaleLanguageDefinition definition : definitions) {
            System.out.printf("%s | %s\n", definition.getCode(), definition.getName());
        }

        LocaleLanguageDefinition definition;

        definition = repository.getLanguageDefinition("ru", displayLocale);
        Assert.assertEquals("ru", definition.getCode());
        Assert.assertEquals("russe", definition.getName());

        definition = repository.getLanguageDefinition("en", displayLocale);
        Assert.assertEquals("en", definition.getCode());
        Assert.assertEquals("anglais", definition.getName());

        definition = repository.getLanguageDefinition("uk", displayLocale);
        Assert.assertNull(definition);
    }
}
