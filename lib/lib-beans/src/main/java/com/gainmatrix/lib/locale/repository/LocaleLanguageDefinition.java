package com.gainmatrix.lib.locale.repository;

import com.gainmatrix.lib.serialization.SerialVersionUID;

import java.io.Serializable;

/**
 * Определение языка
 */
public class LocaleLanguageDefinition implements Serializable {

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    /**
     * Код языка
     * @see java.util.Locale#getLanguage()
     */
    private final String code;

    /**
     * Название языка
     * @see java.util.Locale#getDisplayLanguage(java.util.Locale)
     */
    private final String name;

    public LocaleLanguageDefinition(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
