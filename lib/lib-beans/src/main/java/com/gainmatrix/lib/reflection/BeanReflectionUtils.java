package com.gainmatrix.lib.reflection;

import com.google.common.base.Preconditions;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public final class BeanReflectionUtils {

    private static final int ROOT_LEVEL = 0;

    private BeanReflectionUtils() {
    }

    public static <T> boolean equal(T bean1, T bean2) {
        return equal(bean1, bean2, ROOT_LEVEL);
    }

    public static <T> boolean equal(T bean1, T bean2, int level) {
        try {
            checkEqual(bean1, bean2, level);
        } catch (BeanReflectionMismatchException e) {
            return false;
        }

        return true;
    }

    public static <T> void checkEqual(T bean1, T bean2) {
        checkEqual(bean1, bean2, ROOT_LEVEL);
    }

    public static <T> void checkEqual(T bean1, T bean2, int level) {
        Preconditions.checkNotNull(bean1, "Bean 1 is null");
        Preconditions.checkNotNull(bean2, "Bean 2 is null");
        Preconditions.checkState(level >= ROOT_LEVEL, "Level is negative");

        // Get common class for both beans
        Class<?> beanClass;

        if (bean1.getClass().isAssignableFrom(bean2.getClass())) {
            beanClass = bean1.getClass();
        } else if (bean2.getClass().isAssignableFrom(bean1.getClass())) {
            beanClass = bean2.getClass();
        } else {
            String message = String.format("Class mismatch between %s and %s",
                bean1.getClass().getCanonicalName(),
                bean2.getClass().getCanonicalName()
            );
            throw new IllegalArgumentException(message);
        }

        // Get bean class information
        BeanInfo beanInfo;

        try {
            beanInfo = Introspector.getBeanInfo(beanClass);
        } catch (IntrospectionException e) {
            throw new IllegalStateException("Fail to get bean info for class " + beanClass, e);
        }

        // Check are all properties equal
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            if ("class".equals(propertyDescriptor.getName())) {
                continue;
            }

            checkEqualProperties(bean1, bean2, level, propertyDescriptor);
        }
    }

    private static <T> void checkEqualProperties(T bean1, T bean2, int level, PropertyDescriptor propertyDescriptor) {
        // Requested method must be getter with no arguments
        Method propertyGetter = propertyDescriptor.getReadMethod();
        if ((propertyGetter == null) || (propertyGetter.getParameterTypes().length != 0)) {
            return;
        }

        // Get property class and name
        Class<?> propertyClass = propertyGetter.getReturnType();
        String propertyName = propertyDescriptor.getDisplayName();

        // Get both values
        Object value1;
        Object value2;

        try {
            value1 = propertyGetter.invoke(bean1);
        } catch (Exception e) {
            throw new IllegalStateException("Fail to invoke bean 1 getter for " + propertyName, e);
        }

        try {
            value2 = propertyGetter.invoke(bean2);
        } catch (Exception e) {
            throw new IllegalStateException("Fail to invoke bean 2 getter for " + propertyName, e);
        }

        // Compare two values
        if ((value1 != null) && (value2 != null)) {
            checkEqualValues(value1, value2, level, propertyClass, propertyName);
        } else if ((value1 == null) && (value2 != null)) {
            throw new BeanReflectionMismatchException("Bean 1 value is null for " + propertyName);
        } else if ((value1 != null) && (value2 == null)) {
            throw new BeanReflectionMismatchException("Bean 2 value is null for " + propertyName);
        }
    }

    private static void checkEqualValues(Object value1, Object value2, int level,
        Class<?> propertyClass, String propertyName)
    {
        Preconditions.checkNotNull(value1, "Value 1 is null");
        Preconditions.checkNotNull(value1, "Value 2 is null");
        Preconditions.checkNotNull(propertyClass, "Property class is null");
        Preconditions.checkNotNull(propertyName, "Property name is null");

        if (propertyClass.isPrimitive()) {
            checkEqualPrimitives(value1, value2, propertyName);
        } else if (Set.class.isAssignableFrom(propertyClass)) {
            checkEqualSets((Set) value1, (Set) value2, propertyName);
        } else if (Collection.class.isAssignableFrom(propertyClass)) {
            checkEqualCollections((Collection) value1, (Collection) value2, propertyName);
        } else if (Map.class.isAssignableFrom(propertyClass)) {
            checkEqualMaps((Map) value1, (Map) value2, propertyName);
        } else if (Date.class.isAssignableFrom(propertyClass)) {
            checkEqualDates((Date) value1, (Date) value2, propertyName);
        } else if (propertyClass.getCanonicalName().startsWith("java.lang")) {
            checkEqualObjects(value1, value2, propertyName);
        } else if (propertyClass.getCanonicalName().startsWith("java.math")) {
            checkEqualObjects(value1, value2, propertyName);
        } else {
            if (level > ROOT_LEVEL) {
                checkEqual(value1, value2, level - 1);
            }
        }
    }

    private static void checkEqualPrimitives(Object value1, Object value2, String propertyName) {
        if (!value1.equals(value2)) {
            throw new BeanReflectionMismatchException("Values differ for " + propertyName);
        }
    }

    private static void checkEqualSets(Set<?> set1, Set<?> set2, String propertyName) {
        if (set1.size() != set2.size()) {
            throw new BeanReflectionMismatchException("Set size mismatch for " + propertyName);
        }
    }

    private static void checkEqualCollections(Collection<?> collection1, Collection<?> collection2,
        String propertyName)
    {
        if (collection1.size() != collection2.size()) {
            throw new BeanReflectionMismatchException("Collection size mismatch for " + propertyName);
        }
    }

    private static void checkEqualMaps(Map<?, ?> map1, Map<?, ?> map2, String propertyName) {
        if (map1.size() != map2.size()) {
            throw new BeanReflectionMismatchException("Map size mismatch for " + propertyName);
        }
    }

    private static void checkEqualDates(Date value1, Date value2, String propertyName) {
        long date1 = value1.getTime();
        long date2 = value2.getTime();
        if (date1 != date2) {
            throw new BeanReflectionMismatchException("Values differ for " + propertyName);
        }
    }

    private static void checkEqualObjects(Object value1, Object value2, String propertyName) {
        if (!value1.equals(value2)) {
            throw new BeanReflectionMismatchException("Values differ for " + propertyName);
        }
    }

    public static <T> boolean contains(T bean, Collection<T> collection) {
        Preconditions.checkNotNull(bean, "Bean is null");
        Preconditions.checkNotNull(collection, "Collection is null");

        for (T item : collection) {
            if (equal(bean, item)) {
                return true;
            }
        }

        return false;
    }

    public static <T> void checkContains(T bean, Collection<T> collection) {
        if (!contains(bean, collection)) {
            throw new BeanReflectionMismatchException();
        }
    }

}
