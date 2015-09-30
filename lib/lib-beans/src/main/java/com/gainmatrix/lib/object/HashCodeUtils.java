package com.gainmatrix.lib.object;

import java.lang.reflect.Array;

/**
 * Hash code utils generator
 *
 * @see <a href="http://www.javapractices.com/topic/TopicAction.do?Id=28">Implementing hashCode</a>
 * @see <a href="http://www.amazon.com/exec/obidos/ASIN/0201310058/ref=nosim/javapractices-20">
 *     Recommendations of Effective Java, by Joshua Bloch</a>
 */
public final class HashCodeUtils {

    private static final int HASH_PRIME_SEED = 31;

    private static final int HASH_PRIME_MULTIPLIER = 37;

    private static final int BOOL_F_HASH = 1231;

    private static final int BOOL_T_HASH = 1237;

    private static final int SHIFT_32 = 32;

    private HashCodeUtils() {
    }

    public static int init() {
        return HASH_PRIME_SEED;
    }

    public static int hash(int seed, Boolean value) {
        if (value != null) {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        } else {
            return seed;
        }
    }

    public static int hash(int seed, boolean value) {
        return HASH_PRIME_MULTIPLIER * seed + (value ? BOOL_F_HASH : BOOL_T_HASH);
    }

    public static int hash(int seed, Character value) {
        if (value != null) {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        } else {
            return seed;
        }
    }

    public static int hash(int seed, char value) {
        return HASH_PRIME_MULTIPLIER * seed + (int) value;
    }

    public static int hash(int seed, Integer value) {
        if (value != null) {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        } else {
            return seed;
        }
    }

    public static int hash(int seed, int value) {
        return HASH_PRIME_MULTIPLIER * seed + value;
    }

    public static int hash(int seed, Long value) {
        if (value != null) {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        } else {
            return seed;
        }
    }

    public static int hash(int seed, long value) {
        return HASH_PRIME_MULTIPLIER * seed + (int) (value ^ (value >>> SHIFT_32));
    }

    public static int hash(int seed, Short value) {
        if (value != null) {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        } else {
            return seed;
        }
    }

    public static int hash(int seed, short value) {
        return HASH_PRIME_MULTIPLIER * seed + (int) value;
    }

    public static int hash(int seed, Byte value) {
        if (value != null) {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        } else {
            return seed;
        }
    }

    public static int hash(int seed, byte value) {
        return HASH_PRIME_MULTIPLIER * seed + (int) value;
    }

    public static int hash(int seed, Double value) {
        if (value != null) {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        } else {
            return seed;
        }
    }

    public static int hash(int seed, double value) {
        long bits = Double.doubleToLongBits(value);
        return HASH_PRIME_MULTIPLIER * seed + (int) (bits ^ (bits >>> SHIFT_32));
    }

    public static int hash(int seed, Float value) {
        if (value != null) {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        } else {
            return seed;
        }
    }

    public static int hash(int seed, float value) {
        return HASH_PRIME_MULTIPLIER * seed + Float.floatToIntBits(value);
    }

    public static int hash(int seed, Object value) {
        if (value == null) {
            return seed;
        } else if (value.getClass().isArray()) {
            int result = HASH_PRIME_MULTIPLIER * seed;
            for (int i = 0, size = Array.getLength(value); i < size; i++) {
                Object item = Array.get(value, i);
                result = hash(result, item);
            }
            return result;
        } else {
            return HASH_PRIME_MULTIPLIER * seed + value.hashCode();
        }
    }

}
