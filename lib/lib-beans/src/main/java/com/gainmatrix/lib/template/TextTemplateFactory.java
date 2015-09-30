package com.gainmatrix.lib.template;

import java.util.Locale;

/**
 * Абстракция фабрики шаблонов
 */
public interface TextTemplateFactory {

    /**
     * Запрос шаблона по имени
     * @param name Имя шаблона
     * @param locale Требуемый вариант шаблона (локаль)
     * @return Шаблон либо null, если шаблон не найден
     * @throws TextTemplateException Исключение при невозможности найти или загрузить шаблон с таким именем
     */
    TextTemplate getTemplate(String name, Locale locale) throws TextTemplateException;

}
