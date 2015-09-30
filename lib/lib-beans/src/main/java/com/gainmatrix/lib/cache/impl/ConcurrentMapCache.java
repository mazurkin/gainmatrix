package com.gainmatrix.lib.cache.impl;

import com.gainmatrix.lib.cache.AbstractCache;
import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Кэш на основе java.util.concurrent.ConcurrentMap
 * @param <K> Тип ключа
 * @param <T> Тип значения
 * @see java.util.concurrent.ConcurrentMap
 */
public class ConcurrentMapCache<K, T> implements AbstractCache<K, T> {

    private final ConcurrentMap<K, T> map;

    public ConcurrentMapCache() {
        this(new ConcurrentHashMap<K, T>());
    }

    public ConcurrentMapCache(ConcurrentMap<K, T> map) {
        Preconditions.checkNotNull(map, "Map is null");
        this.map = map;
    }

    @Override
    public void put(K key, T object) {
        map.put(key, object);
    }

    @Override
    public T get(K key) {
        return map.get(key);
    }

    @Override
    public Map<K, T> get(Collection<K> keys) {
        Map<K, T> result = new HashMap<K, T>(keys.size());

        for (K key : keys) {
            T value = map.get(key);
            if (value != null) {
                result.put(key, value);
            }
        }

        return result;
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean exists(K key) {
        return map.containsKey(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Set<K> getKeys() {
        return new HashSet<K>(map.keySet());
    }

    @Override
    public boolean add(K key, T object) {
        T previous = map.putIfAbsent(key, object);
        return previous == null;
    }

    @Override
    public boolean replace(K key, T object) {
        T previous = map.replace(key, object);
        return previous != null;
    }

}
