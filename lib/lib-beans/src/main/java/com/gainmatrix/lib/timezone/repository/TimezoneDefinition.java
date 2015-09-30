package com.gainmatrix.lib.timezone.repository;

import com.gainmatrix.lib.serialization.SerialVersionUID;

import java.io.Serializable;

/**
 * Определение таймзоны
 */
public class TimezoneDefinition implements Serializable {

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    /**
     * Код таймзоны
     * @see java.util.TimeZone#getID()
     */
    private final String code;

    /**
     * Имя таймзоны
     * @see java.util.TimeZone#getDisplayName(java.util.Locale)
     */
    private final String name;

    /**
     * Смещение таймзоны относительно UTC в миллисекундах
     * @see java.util.TimeZone#getOffset(long)
     */
    private final int offset;

    public TimezoneDefinition(String code, String name, int offset) {
        this.code = code;
        this.name = name;
        this.offset = offset;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getOffset() {
        return offset;
    }
}
