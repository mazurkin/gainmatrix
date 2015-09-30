package com.gainmatrix.lib.cache.proxy;

import com.gainmatrix.lib.cache.AbstractCache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Прокси обеспечивающий конкурентный доступ через synchronized к дочернему кэшу не поддерживающему конкурентный
 * доступ
 * @param <K> Тип ключа
 * @param <T> Тип значения
 */
public class SynchronizedCacheProxy<K, T> implements AbstractCache<K, T> {

    private final AbstractCache<K, T> cache;

    public SynchronizedCacheProxy(AbstractCache<K, T> cache) {
        this.cache = cache;
    }

    @Override
    public synchronized boolean add(K key, T object) {
        return cache.add(key, object);
    }

    @Override
    public synchronized void clear() {
        cache.clear();
    }

    @Override
    public synchronized boolean exists(K key) {
        return cache.exists(key);
    }

    @Override
    public synchronized T get(K key) {
        return cache.get(key);
    }

    @Override
    public synchronized Map<K, T> get(Collection<K> keys) {
        return cache.get(keys);
    }

    @Override
    public synchronized Set<K> getKeys() {
        return new HashSet<K>(cache.getKeys());
    }

    @Override
    public synchronized void put(K key, T object) {
        cache.put(key, object);
    }

    @Override
    public synchronized void remove(K key) {
        cache.remove(key);
    }

    @Override
    public synchronized boolean replace(K key, T object) {
        return cache.replace(key, object);
    }

    @Override
    public synchronized int size() {
        return cache.size();
    }

}
