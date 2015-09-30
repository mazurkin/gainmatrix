package com.gainmatrix.lib.locale.repository;

import com.gainmatrix.lib.serialization.SerialVersionUID;

import java.io.Serializable;

/**
 * Определения страны
 */
public class LocaleCountryDefinition implements Serializable {

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    /**
     * Код страны
     * @see java.util.Locale#getCountry()
     */
    private final String code;

    /**
     * Название страны
     * @see java.util.Locale#getDisplayCountry(java.util.Locale)
     */
    private final String name;

    public LocaleCountryDefinition(String code, String name) {
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
