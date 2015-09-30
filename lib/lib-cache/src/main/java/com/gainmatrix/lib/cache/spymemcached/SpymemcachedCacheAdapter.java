package com.gainmatrix.lib.cache.spymemcached;

import com.gainmatrix.lib.business.exception.SystemIntegrityException;
import com.gainmatrix.lib.cache.AbstractCache;
import com.google.common.base.Preconditions;
import net.spy.memcached.MemcachedClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Адартер кэша к Spymemcached
 * @param <T> Тип значения
 */
public class SpymemcachedCacheAdapter<T> implements AbstractCache<String, T> {

    private static final int DEFAULT_EXPIRATION_SEC = 600;

    private static final long DEFAULT_TIMEOUT_MS = 1000;

    private static final int MAXIMUM_EXPIRATION_SEC = 60 * 60 * 24 * 30;

    private final MemcachedClient client;

    private int expirationSec = DEFAULT_EXPIRATION_SEC;

    private long timeoutMs = DEFAULT_TIMEOUT_MS;

    private String keyPrefix = null;

    public SpymemcachedCacheAdapter(MemcachedClient client) {
        Preconditions.checkNotNull(client, "Client is null");
        this.client = client;
    }

    @Override
    public boolean add(String key, T object) {
        String fullKey = composeFullKey(key);
        Future<Boolean> operation = client.add(fullKey, expirationSec, object);
        return waitOperationResult(operation);
    }

    @Override
    public void clear() {
        Future<Boolean> operation = client.flush();
        boolean succeed = waitOperationResult(operation);
        if (!succeed) {
            throw new IllegalStateException("Clear operation failed");
        }
    }

    @Override
    public boolean exists(String key) {
        String fullKey = composeFullKey(key);
        T value = get(fullKey);
        return value != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(String key) {
        String fullKey = composeFullKey(key);
        Future<Object> operation = client.asyncGet(fullKey);
        return (T) waitOperationResult(operation);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, T> get(Collection<String> keys) {
        List<String> fullKeys = new ArrayList<String>(keys.size());
        for (String key : keys) {
            String fullKey = composeFullKey(key);
            fullKeys.add(fullKey);
        }

        Future<Map<String, Object>> operation = client.asyncGetBulk(fullKeys);
        return (Map<String, T>) waitOperationResult(operation);
    }

    @Override
    public Set<String> getKeys() {
        throw new UnsupportedOperationException("method getKeys() is not supported");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("method size() is not supported");
    }

    @Override
    public void put(String key, T object) {
        String fullKey = composeFullKey(key);
        Future<Boolean> operation = client.set(fullKey, expirationSec, object);
        boolean succeed = waitOperationResult(operation);
        if (!succeed) {
            throw new IllegalStateException("Put operation failed");
        }
    }

    @Override
    public void remove(String key) {
        String fullKey = composeFullKey(key);
        Future<Boolean> operation = client.delete(fullKey);
        waitOperationResult(operation);
    }

    @Override
    public boolean replace(String key, T object) {
        String fullKey = composeFullKey(key);
        Future<Boolean> operation = client.replace(fullKey, expirationSec, object);
        return waitOperationResult(operation);
    }

    protected <T> T waitOperationResult(Future<T> future) {
        try {
            return future.get(timeoutMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new SystemIntegrityException("Spymemcached operation is canceled", e);
        } catch (TimeoutException e) {
            throw new SystemIntegrityException("Spymemcached operation is timed out", e);
        } catch (ExecutionException e) {
            throw new SystemIntegrityException("Spymemcached operation failed", e);
        }
    }

    protected String composeFullKey(String key) {
        if (keyPrefix != null) {
            return keyPrefix + ":" + key;
        } else {
            return key;
        }
    }

    /**
     * Префикс ключей кэша. Если префикс установлен, то реальный ключ будет составлен из этого префикса и переданного
     * ключа значения. Если префикс не установлен (равен null), то в качестве реального ключа будет использован
     * переданный ключ значения
     * @param keyPrefix Значение префикса
     */
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    /**
     * Время ожидания ответа от сервера Memcached
     * @param timeoutMs Время ожидания в миллисекундах
     */
    public void setTimeoutMs(long timeoutMs) {
        Preconditions.checkArgument(timeoutMs > 0);
        this.timeoutMs = timeoutMs;
    }

    /**
     * Время хранения значений
     * @param expirationSec Время хранения значения в секундах (не больше 30 дней)
     */
    public void setExpirationSec(int expirationSec) {
        Preconditions.checkArgument(expirationSec < MAXIMUM_EXPIRATION_SEC);
        this.expirationSec = expirationSec;
    }

}
