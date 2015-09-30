package com.gainmatrix.lib.cache.impl;

import com.gainmatrix.lib.cache.AbstractCache;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Кэш который ничего не сохраняет
 * @param <K> Тип ключа
 * @param <T> Тип значения
 */
public class NullCache<K, T> implements AbstractCache<K, T> {

    @Override
    public void put(K key, T object) {
        // do nothing
    }

    @Override
    public T get(K key) {
        return null;
    }

    @Override
    public Map<K, T> get(Collection<K> keys) {
        return Collections.emptyMap();
    }

    @Override
    public void remove(K key) {
        // do nothing
    }

    @Override
    public void clear() {
        // do nothing
    }

    @Override
    public boolean exists(K key) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> getKeys() {
        return Collections.emptySet();
    }

    @Override
    public boolean add(K key, T object) {
        return true;
    }

    @Override
    public boolean replace(K key, T object) {
        return true;
    }

}
