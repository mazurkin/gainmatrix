package com.gainmatrix.lib.web.attribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Интерфейс для реализации публикаторов общеупотребимых аттрибутов. Такими общеупотребимыми
 * аттрибутами могут быть: текущая локаль, текущий язык, текущая таймозона, время поступления запроса,
 * и так далее.
 */
public interface AttributePublisher {

    /**
     * Публикация общеупотребимых аттрибутов
     * @param request HTTP-запрос
     */
    void publish(HttpServletRequest request);

}
