package com.gainmatrix.lib.locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import java.util.Locale;

/**
 * Инициализация локали по-умолчанию для всего приложения
 */
public class DefaultLocaleInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLocaleInitializer.class);

    private Locale locale;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Locale currentLocale = Locale.getDefault();

        if (locale != null) {
            LOGGER.info("Default locale was [{}] and is changed to [{}]", currentLocale, locale);
            Locale.setDefault(locale);
        } else {
            LOGGER.info("Default locale is [{}]", currentLocale);
        }
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
