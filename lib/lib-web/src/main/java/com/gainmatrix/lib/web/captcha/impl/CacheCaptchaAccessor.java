package com.gainmatrix.lib.web.captcha.impl;

import com.gainmatrix.lib.cache.AbstractCache;
import com.gainmatrix.lib.random.RandomTokenGenerator;
import com.gainmatrix.lib.web.captcha.CaptchaAccessor;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpSession;

/**
 * Хранение кодов проверок во внешнем кэше
 */
public class CacheCaptchaAccessor implements CaptchaAccessor {

    private static final int DEFAULT_TOKEN_LENGTH = 4;

    private RandomTokenGenerator randomTokenGenerator;

    private AbstractCache<String, String> cache;

    private int tokenLength = DEFAULT_TOKEN_LENGTH;

    @Override
    public String obtainAnswer(HttpSession session, String formKey) {
        Preconditions.checkNotNull(session, "Session is null");

        String fullKey = composeFullKey(session, formKey);

        String token = cache.get(fullKey);
        if (token == null) {
            token = randomTokenGenerator.generate(tokenLength);
            cache.put(fullKey, token);
        }

        return token;
    }

    @Override
    public boolean checkAnswer(HttpSession session, String formKey, String answer) {
        Preconditions.checkNotNull(session, "Session is null");

        String fullKey = composeFullKey(session, formKey);

        String token = cache.get(fullKey);
        if (token == null) {
            return false;
        }

        boolean result = token.equals(answer);
        cache.remove(fullKey);

        return result;
    }

    protected String composeFullKey(HttpSession session, String key) {
        if (key != null) {
            return session.getId() + ":" + key;
        } else {
            return session.getId();
        }
    }

    @Required
    public void setRandomTokenGenerator(RandomTokenGenerator randomTokenGenerator) {
        this.randomTokenGenerator = randomTokenGenerator;
    }

    @Required
    public void setCache(AbstractCache<String, String> cache) {
        this.cache = cache;
    }

    public void setTokenLength(int tokenLength) {
        this.tokenLength = tokenLength;
    }
}
