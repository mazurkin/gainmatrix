package com.gainmatrix.lib.business.entity;

import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Helper functions for identified entity
 */
public final class IdentifiedEntityUtils {

    private IdentifiedEntityUtils() {
    }

    /**
     * Запрос списка идентификаторов у коллекции сущностей
     * @param entities Коллекция сущностей
     * @param <I> Тип идентификатора сущности
     * @param <T> Тип сущности
     * @return Набор уникальных идентификаторов
     */
    public static <I, T extends IdentifiedEntity<I>> Set<I> extractIds(Collection<T> entities) {
        if ((entities == null) || (entities.isEmpty())) {
            return Collections.emptySet();
        }

        Set<I> ids = new HashSet<I>(entities.size());

        for (T entity : entities) {
            I id = entity.getId();
            if (id != null) {
                ids.add(entity.getId());
            } else {
                throw new IllegalArgumentException("Entity id must be non-null");
            }
        }

        return Collections.unmodifiableSet(ids);
    }

    /**
     * Запрос списка идентификаторов у массива сущностей
     * @param entities Массив сущностей
     * @param <I> Тип идентификатора сущности
     * @param <T> Тип сущности
     * @return Набор уникальных идентификаторов
     */
    public static <I, T extends IdentifiedEntity<I>> Set<I> extractIds(T[] entities) {
        if ((entities == null) || (entities.length == 0)) {
            return Collections.emptySet();
        }

        Set<I> ids = new HashSet<I>(entities.length);

        for (T entity : entities) {
            I id = entity.getId();
            if (id != null) {
                ids.add(entity.getId());
            } else {
                throw new IllegalArgumentException("Entity id must be non-null");
            }
        }

        return Collections.unmodifiableSet(ids);
    }

    /**
     * Проверка того, что у двух сущностей одинаковые идентификаторы
     * @param entity1 Сущность 1
     * @param entity2 Сущность 2
     * @param <T> Тип сущности
     * @return Возвращает true, если обе сущности имеют идентификаторы и они равны
     */
    public static <T extends IdentifiedEntity> boolean isSameId(T entity1, T entity2) {
        Preconditions.checkNotNull(entity1);
        Preconditions.checkNotNull(entity2);

        return (entity1.getId() != null) && (entity2.getId() != null) &&
            (entity1.getId().equals(entity2.getId()));
    }

    /**
     * Проверка того, что сущности одинаковы
     * @param entity1 Сущность 1
     * @param entity2 Сущность 2
     * @param <T> Тип сущности
     * @return Возвращает true, если обе сущности не null и равны
     */
    public static <T> boolean isEqual(T entity1, T entity2) {
        return (entity1 != null) && (entity2 != null) && (entity1.equals(entity2));
    }

    /**
     * Формирование контейнера отношений идентификатор->сущность из коллекции сущностей
     * @param entities Коллекция сущностей
     * @param <I> Тип идентификатора
     * @param <T> Тип сущности
     * @return Контейнер отношений
     */
    public static <I, T extends IdentifiedEntity<I>> Map<I, T> mapById(Collection<T> entities) {
        if ((entities == null) || (entities.isEmpty())) {
            return Collections.emptyMap();
        }

        Map<I, T> result = new HashMap<I, T>(entities.size());

        for (T entity : entities) {
            result.put(entity.getId(), entity);
        }

        return Collections.unmodifiableMap(result);
    }

}
