package com.gainmatrix.lib.locale.repository.impl;

import com.gainmatrix.lib.locale.repository.LocaleLanguageDefinition;
import com.gainmatrix.lib.locale.repository.LocaleLanguageRepository;
import com.gainmatrix.lib.preconditions.Preconditions2;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Репозиторий языков на основе справочника в JDK
 */
public class DefaultLocaleLanguageRepository implements LocaleLanguageRepository {

    private static final String DEFAULT_COUNTRY = "US";

    private Set<String> allowedCodes;

    @Override
    public List<LocaleLanguageDefinition> getLanguageDefinitions(Locale displayLocale) {
        Locale usedLocale;

        if (displayLocale != null) {
            usedLocale = displayLocale;
        } else {
            usedLocale = Locale.getDefault();
        }

        String[] codes = Locale.getISOLanguages();

        List<LocaleLanguageDefinition> definitions = new ArrayList<LocaleLanguageDefinition>(codes.length);

        Set<String> foundCoded = new HashSet<String>(codes.length);

        for (String code : codes) {
            if ((allowedCodes != null) && (!allowedCodes.contains(code))) {
                continue;
            }

            Locale locale = new Locale(code, DEFAULT_COUNTRY);

            // Некоторые коды волшебным образом преобразуются (jy -> ji), поэтому исключаем их чтобы не повторялись
            if (!foundCoded.contains(locale.getLanguage())) {
                foundCoded.add(locale.getLanguage());
            } else {
                continue;
            }

            String languageCode = locale.getLanguage();
            String languageName = locale.getDisplayLanguage(usedLocale);
            LocaleLanguageDefinition definition = new LocaleLanguageDefinition(languageCode, languageName);

            definitions.add(definition);
        }

        Comparator<LocaleLanguageDefinition> definitionComparator =
            new Comparator<LocaleLanguageDefinition>() {
                @Override
                public int compare(LocaleLanguageDefinition o1, LocaleLanguageDefinition o2) {
                    String name1 = o1.getName();
                    String name2 = o2.getName();
                    return name1.compareTo(name2);
                }
            };

        Collections.sort(definitions, definitionComparator);

        return Collections.unmodifiableList(definitions);
    }

    @Override
    public LocaleLanguageDefinition getLanguageDefinition(String code, Locale displayLocale) {
        Preconditions2.checkNotBlank(code, "Code is not set");

        Locale usedLocale;

        if (displayLocale != null) {
            usedLocale = displayLocale;
        } else {
            usedLocale = Locale.getDefault();
        }

        // Проверка наличия указанного кода в списке кодов
        String[] codes = Locale.getISOLanguages();

        if ((allowedCodes != null) && (!allowedCodes.contains(code))) {
            return null;
        }

        if (!ArrayUtils.contains(codes, code)) {
            return null;
        }

        // Результат
        Locale locale = new Locale(code, DEFAULT_COUNTRY);
        String languageCode = locale.getLanguage();
        String languageName = locale.getDisplayLanguage(usedLocale);
        return new LocaleLanguageDefinition(languageCode, languageName);
    }

    public void setAllowedCodes(Set<String> allowedCodes) {
        this.allowedCodes = allowedCodes;
    }
}
