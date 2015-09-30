package com.gainmatrix.lib.web.captcha.impl;

import com.gainmatrix.lib.random.RandomTokenGenerator;
import com.gainmatrix.lib.web.captcha.CaptchaAccessor;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpSession;

/**
 * Хранение кодов проверок в самой сессии в виде аттрибутов
 */
public class SessionCaptchaAccessor implements CaptchaAccessor {

    private static final int DEFAULT_TOKEN_LENGTH = 4;

    private static final String DEFAULT_SESSION_KEY = "gainmatrix.captcha.key";

    private RandomTokenGenerator randomTokenGenerator;

    private int tokenLength = DEFAULT_TOKEN_LENGTH;

    private String sessionKey = DEFAULT_SESSION_KEY;

    @Override
    public String obtainAnswer(HttpSession session, String formKey) {
        Preconditions.checkNotNull(session, "Session is null");

        String fullKey = composeFullKey(formKey);

        String token = (String) session.getAttribute(fullKey);
        if (token == null) {
            token = randomTokenGenerator.generate(tokenLength);
            session.setAttribute(fullKey, token);
        }

        return token;
    }

    @Override
    public boolean checkAnswer(HttpSession session, String formKey, String answer) {
        Preconditions.checkNotNull(session, "Session is null");

        String fullKey = composeFullKey(formKey);

        String token = (String) session.getAttribute(fullKey);
        if (token == null) {
            return false;
        }

        boolean result = token.equals(answer);
        session.removeAttribute(fullKey);

        return result;
    }

    protected String composeFullKey(String key) {
        if (key != null) {
            return sessionKey + "." + key;
        } else {
            return sessionKey;
        }
    }

    @Required
    public void setRandomTokenGenerator(RandomTokenGenerator randomTokenGenerator) {
        this.randomTokenGenerator = randomTokenGenerator;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public void setTokenLength(int tokenLength) {
        this.tokenLength = tokenLength;
    }
}
