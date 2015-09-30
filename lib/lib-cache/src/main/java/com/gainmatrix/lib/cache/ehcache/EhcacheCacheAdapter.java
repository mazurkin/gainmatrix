package com.gainmatrix.lib.cache.ehcache;

import com.gainmatrix.lib.cache.AbstractCache;
import com.google.common.base.Preconditions;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Адаптер к кэшу Ehcache
 * @param <K> Тип ключа
 * @param <T> Тип значения
 */
public class EhcacheCacheAdapter<K extends Serializable, T extends Serializable> implements AbstractCache<K, T> {

    private final Cache client;

    public EhcacheCacheAdapter(Cache client) {
        Preconditions.checkNotNull(client, "Client is null");
        this.client = client;
    }

    @Override
    public boolean add(K key, T object) {
        Element element = new Element(key, object);
        Element previous = client.putIfAbsent(element);
        return previous == null;
    }

    @Override
    public void clear() {
        client.removeAll();
    }

    @Override
    public boolean exists(K key) {
        return client.isKeyInCache(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(K key) {
        Element element = client.get(key);
        return (element != null) ? (T) element.getValue() : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<K, T> get(Collection<K> keys) {
        Map<Object, Element> values = client.getAll(keys);

        Map<K, T> result = new HashMap<K, T>();

        for (Map.Entry<Object, Element> entry : values.entrySet()) {
            result.put((K) entry.getValue().getKey(), (T) entry.getValue().getValue());
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> getKeys() {
        List<K> keys = client.getKeysWithExpiryCheck();
        return new HashSet<K>(keys);
    }

    @Override
    public void put(K key, T object) {
        Element element = new Element(key, object);
        client.put(element);
    }

    @Override
    public void remove(K key) {
        client.remove(key);
    }

    @Override
    public boolean replace(K key, T object) {
        Element element = new Element(key, object);
        Element previous = client.replace(element);
        return previous != null;
    }

    @Override
    public int size() {
        return client.getSize();
    }

}
