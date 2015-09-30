package com.gainmatrix.lib.locale.repository.impl;

import com.gainmatrix.lib.locale.repository.LocaleCountryDefinition;
import com.gainmatrix.lib.locale.repository.LocaleCountryRepository;
import com.gainmatrix.lib.preconditions.Preconditions2;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Репозиторий стран на основе справочника в JDK
 */
public class DefaultLocaleCountryRepository implements LocaleCountryRepository {

    private static final String DEFAULT_LANGUAGE = "en";

    @Override
    public List<LocaleCountryDefinition> getCountryDefinitions(Locale displayLocale) {
        Locale usedLocale;

        if (displayLocale != null) {
            usedLocale = displayLocale;
        } else {
            usedLocale = Locale.getDefault();
        }

        String[] codes = Locale.getISOCountries();

        List<LocaleCountryDefinition> definitions = new ArrayList<LocaleCountryDefinition>(codes.length);

        for (String code : codes) {
            Locale locale = new Locale(DEFAULT_LANGUAGE, code);

            String localeCode = locale.getCountry();
            String localeName = locale.getDisplayCountry(usedLocale);
            LocaleCountryDefinition definition = new LocaleCountryDefinition(localeCode, localeName);

            definitions.add(definition);
        }

        Comparator<LocaleCountryDefinition> definitionComparator =
            new Comparator<LocaleCountryDefinition>() {
                @Override
                public int compare(LocaleCountryDefinition o1, LocaleCountryDefinition o2) {
                    String name1 = o1.getName();
                    String name2 = o2.getName();
                    return name1.compareTo(name2);
                }
            };

        Collections.sort(definitions, definitionComparator);

        return Collections.unmodifiableList(definitions);
    }

    @Override
    public LocaleCountryDefinition getCountryDefinition(String code, Locale displayLocale) {
        Preconditions2.checkNotBlank(code, "Code is not set");

        Locale usedLocale;

        if (displayLocale != null) {
            usedLocale = displayLocale;
        } else {
            usedLocale = Locale.getDefault();
        }

        // Проверка наличия указанного кода
        String[] codes = Locale.getISOCountries();

        if (!ArrayUtils.contains(codes, code)) {
            return null;
        }

        // Результат
        Locale locale = new Locale(DEFAULT_LANGUAGE, code);
        String localeCode = locale.getCountry();
        String localeName = locale.getDisplayCountry(usedLocale);
        return new LocaleCountryDefinition(localeCode, localeName);
    }

}
