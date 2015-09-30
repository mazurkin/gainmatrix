package com.gainmatrix.lib.web.captcha;

import javax.servlet.http.HttpSession;

/**
 * Абстракция для работы с кодами проверок
 */
public interface CaptchaAccessor {

    /**
     * Запрос кода проверки для показа пользователю
     * @param session Сессия
     * @param formKey Ключ текущей формы, либо null для глобально-сессионного кода проверки
     * @return Код проверки
     */
    String obtainAnswer(HttpSession session, String formKey);

    /**
     * Проверка введенного кода на правильность. Желательно, чтобы каждая реализация после проверки сбрасывала
     * текущий код, чтобы метод obtainAnswer выдавал новый код. Такая реализация затруднит автоматический подбор
     * кода проверки.
     * @param session Сессия
     * @param formKey Ключ текущей формы, либо null для глобально-сессионного кода проверки
     * @param answer Введенный пользователем код
     * @return Возвращает true, если введенный пользователем код правилен
     * @see CaptchaAccessor#obtainAnswer(javax.servlet.http.HttpSession, java.lang.String)
     */
    boolean checkAnswer(HttpSession session, String formKey, String answer);

}
