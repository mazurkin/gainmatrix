package com.gainmatrix.lib.locale.repository;

import java.util.List;
import java.util.Locale;

/**
 * Репозиторий доступных стран
 */
public interface LocaleCountryRepository {

    /**
     * Запрос списка доступных определений стран
     * @param displayLocale Локаль в которой выводить названия стран
     * @return Список определений стран
     */
    List<LocaleCountryDefinition> getCountryDefinitions(Locale displayLocale);

    /**
     * Запрос определения страны по коду
     * @param code Код страны
     * @param displayLocale Локаль в которой выводить названия стран
     * @return Определение страны
     */
    LocaleCountryDefinition getCountryDefinition(String code, Locale displayLocale);

}
