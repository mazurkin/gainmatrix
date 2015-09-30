package com.gainmatrix.lib.timezone.repository;

import java.util.List;
import java.util.Locale;

/**
 * Репозиторий доступных временных зон
 */
public interface TimezoneRepository {

    /**
     * Запрос доступного списка определений таймзон
     * @param displayLocale Локаль в которой выводить имена таймзон
     * @return Список определений таймзон
     */
    List<TimezoneDefinition> getTimezoneDefinitions(Locale displayLocale);

    /**
     * Запрос определения таймзоны по коду
     * @param code Код тайзоны
     * @param displayLocale Локаль в которой выводить имена таймзон
     * @return Определение тайзоны либо null если таймзона с указанным идентификатором не найдена
     */
    TimezoneDefinition getTimezoneDefinition(String code, Locale displayLocale);

}
