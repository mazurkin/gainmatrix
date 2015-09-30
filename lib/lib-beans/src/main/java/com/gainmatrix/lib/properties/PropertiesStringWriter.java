package com.gainmatrix.lib.properties;

import java.util.Properties;

/**
 * Write all values as string values
 */
public final class PropertiesStringWriter {

    private PropertiesStringWriter() {
    }

    public static void write(Properties properties, String key, String value) {
        properties.put(key, value);
    }

    public static void write(Properties properties, String key, boolean value) {
        String text = String.valueOf(value);
        properties.put(key, text);
    }

    public static void write(Properties properties, String key, int value) {
        String text = String.valueOf(value);
        properties.put(key, text);
    }

    public static void write(Properties properties, String key, long value) {
        String text = String.valueOf(value);
        properties.put(key, text);
    }

    public static void write(Properties properties, String key, double value) {
        String text = String.valueOf(value);
        properties.put(key, text);
    }

    public static void write(Properties properties, String key, float value) {
        String text = String.valueOf(value);
        properties.put(key, text);
    }

    public static void write(Properties properties, String key, byte value) {
        String text = String.valueOf(value);
        properties.put(key, text);
    }

    public static void write(Properties properties, String key, char value) {
        String text = String.valueOf(value);
        properties.put(key, text);
    }

    public static void write(Properties properties, String key, Object value) {
        String text = String.valueOf(value);
        properties.put(key, text);
    }

}
