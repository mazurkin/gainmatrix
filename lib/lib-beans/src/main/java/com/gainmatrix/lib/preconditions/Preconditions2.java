package com.gainmatrix.lib.preconditions;

import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * Class extends base preconditions functionality
 * @see com.google.common.base.Preconditions
 */
public final class Preconditions2 {

    private Preconditions2() {
    }

    public static void checkEqual(Object o1, Object o2, String message) {
        Preconditions.checkNotNull(o1);
        boolean equality = o1.equals(o2);
        Preconditions.checkArgument(equality, message);
    }

    public static void checkEqual(Object o1, Object o2) {
        Preconditions.checkNotNull(o1);
        boolean equality = o1.equals(o2);
        Preconditions.checkArgument(equality);
    }

    public static void checkNotEqual(Object o1, Object o2, String message) {
        Preconditions.checkNotNull(o1);
        boolean equality = o1.equals(o2);
        Preconditions.checkArgument(!equality, message);
    }

    public static void checkNotEqual(Object o1, Object o2) {
        Preconditions.checkNotNull(o1);
        boolean equality = o1.equals(o2);
        Preconditions.checkArgument(!equality);
    }

    public static void checkNotEmpty(String text, String message) {
        boolean empty = StringUtils.isEmpty(text);
        Preconditions.checkArgument(!empty, message);
    }

    public static void checkNotEmpty(String text) {
        boolean empty = StringUtils.isEmpty(text);
        Preconditions.checkArgument(!empty);
    }

    public static void checkNotBlank(String text, String message) {
        boolean blank = StringUtils.isBlank(text);
        Preconditions.checkArgument(!blank, message);
    }

    public static void checkNotBlank(String text) {
        boolean blank = StringUtils.isBlank(text);
        Preconditions.checkArgument(!blank);
    }

    public static void checkEmpty(Collection<?> collection, String message) {
        boolean empty = CollectionUtils.isEmpty(collection);
        Preconditions.checkArgument(empty, message);
    }

    public static void checkEmpty(Collection<?> collection) {
        boolean empty = CollectionUtils.isEmpty(collection);
        Preconditions.checkArgument(empty);
    }

    public static void checkNotEmpty(Collection<?> collection, String message) {
        boolean empty = CollectionUtils.isEmpty(collection);
        Preconditions.checkArgument(!empty, message);
    }

    public static void checkNotEmpty(Collection<?> collection) {
        boolean empty = CollectionUtils.isEmpty(collection);
        Preconditions.checkArgument(!empty);
    }
}
