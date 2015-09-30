package com.gainmatrix.lib.web.resolver.timezone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.TimeZone;

/**
 * Интерфейс для реализации класса определяющего текущую временную зону для клиента
 */
public interface TimezoneResolver {

    /**
     * Запрос клиентской временной зоны
     * @param request HTTP-запрос
     * @return Временная зона на клиенте. Не должен возвращать <em>null</em>.
     */
    TimeZone resolveTimezone(HttpServletRequest request);

    /**
     * Установка клиентской временной зоны
     * @param request Запрос
     * @param response Отклик
     * @param timezone Временная зона
     */
    void setTimezone(HttpServletRequest request, HttpServletResponse response, TimeZone timezone);

}
