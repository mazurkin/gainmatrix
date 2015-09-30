package com.gainmatrix.lib.cache.proxy;

import com.gainmatrix.lib.cache.AbstractCache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Прокси обеспечивающий конкурентный доступ через read-write lock к дочернему кэшу не поддерживающему
 * конкурентный доступ
 * @param <K> Тип ключа
 * @param <T> Тип значения
 */
public class ReadWriteLockCacheProxy<K, T> implements AbstractCache<K, T> {

    private AbstractCache<K, T> cache;

    private ReadWriteLock lock;

    public ReadWriteLockCacheProxy(AbstractCache<K, T> cache) {
        this(cache, false);
    }

    public ReadWriteLockCacheProxy(AbstractCache<K, T> cache, boolean fair) {
        this.cache = cache;
        this.lock = new ReentrantReadWriteLock(fair);
    }

    @Override
    public boolean add(K key, T object) {
        lock.writeLock().lock();
        try {
            return cache.add(key, object);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            cache.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean exists(K key) {
        lock.readLock().lock();
        try {
            return cache.exists(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public T get(K key) {
        lock.readLock().lock();
        try {
            return cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Map<K, T> get(Collection<K> keys) {
        lock.readLock().lock();
        try {
            return cache.get(keys);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Set<K> getKeys() {
        lock.readLock().lock();
        try {
            return new HashSet<K>(cache.getKeys());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void put(K key, T object) {
        lock.writeLock().lock();
        try {
            cache.put(key, object);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void remove(K key) {
        lock.writeLock().lock();
        try {
            cache.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean replace(K key, T object) {
        lock.writeLock().lock();
        try {
            return cache.replace(key, object);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public int size() {
        lock.readLock().lock();
        try {
            return cache.size();
        } finally {
            lock.readLock().unlock();
        }
    }

}
