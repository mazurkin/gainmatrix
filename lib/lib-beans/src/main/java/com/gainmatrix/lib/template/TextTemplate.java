package com.gainmatrix.lib.template;

import java.util.Map;

/**
 * Абстракция шаблонизатора
 */
public interface TextTemplate {

    /**
     * Рендеринг шаблона
     * @param attributes Аттрибуты рендеринга
     * @return Текст
     * @throws TextTemplateException Исключение в случае ошибки
     */
    String render(Map<String, Object> attributes) throws TextTemplateException;

}
