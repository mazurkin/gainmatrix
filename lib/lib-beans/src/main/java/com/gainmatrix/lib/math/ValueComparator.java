package com.gainmatrix.lib.math;

/**
 * Компаратор для наглядного сравнения таких классов как java.math.BigDecimal, java.math.BigInteger, java.util.Date
 * @param <T> Тип сравниваемых значений
 */
public final class ValueComparator<T extends Comparable<T>> {

    private final T source;

    private ValueComparator(T source) {
        this.source = source;
    }

    public static <K extends Comparable<K>> ValueComparator<K> compare(K value) {
        return new ValueComparator<K>(value);
    }

    public boolean isLessThan(T value) {
        return source.compareTo(value) < 0;
    }

    public boolean isMoreThan(T value) {
        return source.compareTo(value) > 0;
    }

    public boolean isEqualTo(T value) {
        return source.compareTo(value) == 0;
    }

    public boolean isNotLessThan(T value) {
        return source.compareTo(value) >= 0;
    }

    public boolean isNotMoreThan(T value) {
        return source.compareTo(value) <= 0;
    }

    public boolean isNotEqualTo(T value) {
        return source.compareTo(value) != 0;
    }

}
