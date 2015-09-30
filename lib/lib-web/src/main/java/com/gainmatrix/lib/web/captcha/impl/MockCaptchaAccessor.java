package com.gainmatrix.lib.web.captcha.impl;

import com.gainmatrix.lib.web.captcha.CaptchaAccessor;
import com.google.common.base.Preconditions;

import javax.servlet.http.HttpSession;

/**
 * Специальный класс для фиксированного кода проверки (в тестах)
 */
public class MockCaptchaAccessor implements CaptchaAccessor {

    private static final String DEFAULT_CODE = "1234";

    private String code = DEFAULT_CODE;

    @Override
    public String obtainAnswer(HttpSession session, String formKey) {
        return code;
    }

    @Override
    public boolean checkAnswer(HttpSession session, String formKey, String answer) {
        return code.equals(answer);
    }

    public void setCode(String code) {
        Preconditions.checkNotNull(code, "Code is null");
        this.code = code;
    }
}
