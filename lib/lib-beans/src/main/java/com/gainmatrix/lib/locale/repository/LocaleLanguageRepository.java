package com.gainmatrix.lib.locale.repository;

import java.util.List;
import java.util.Locale;

/**
 * Репозиторий доступных языков
 */
public interface LocaleLanguageRepository {

    /**
     * Запрос списка доступных определений языков
     * @param displayLocale Локаль в которой выводить имена языков
     * @return Список определения языков
     */
    List<LocaleLanguageDefinition> getLanguageDefinitions(Locale displayLocale);

    /**
     * Запрос определения языка по коду
     * @param code Код языка
     * @param displayLocale Локаль в которой выводить имена языков
     * @return Определения языка либо null если определение не найдено
     */
    LocaleLanguageDefinition getLanguageDefinition(String code, Locale displayLocale);

}
