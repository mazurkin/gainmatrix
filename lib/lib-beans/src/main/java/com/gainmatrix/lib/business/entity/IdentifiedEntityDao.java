package com.gainmatrix.lib.business.entity;

import java.util.Collection;
import java.util.Set;

/**
 * Abstract DAO for an entity with identifier
 */
public interface IdentifiedEntityDao {

    /**
     * Find entity by identifier
     * @param clazz Entity class reference
     * @param id identifier
     * @param <T> Entity class
     * @param <I> Identifier class
     * @return Entity
     */
    <T extends IdentifiedEntity<I>, I> T findById(Class<T> clazz, I id);

    /**
     * Find entity set by collection of identifiers
     * @param clazz Entity class reference
     * @param ids Collection of identifiers
     * @param <T> Entity class
     * @param <I> Identifier class
     * @return Set of entities
     */
    <T extends IdentifiedEntity<I>, I> Set<T> findByIds(Class<T> clazz, Collection<I> ids);

    /**
     * Lock entity by identifier
     * @param clazz Entity class reference
     * @param id Identifier
     * @param <T> Entity class
     * @param <I> Identifier class
     * @return Entity
     */
    <T extends IdentifiedEntity<I>, I> T lockById(Class<T> clazz, I id);

    /**
     * Lock collection of entities by their identifiers in order
     * @param clazz Entity class reference
     * @param ids Collection of identifiers
     * @param <T> Entity class
     * @param <I> Identifier class
     * @return Set of entities
     */
    <T extends IdentifiedEntity<I>, I extends Comparable<I>> Set<T> lockById(Class<T> clazz, Collection<I> ids);

    /**
     * Lock entity
     * @param entity Entity
     * @param <T> Entity class reference
     * @param <I> Identifier class
     */
    <T extends IdentifiedEntity<I>, I> void lock(T entity);

    /**
     * Lock collection of entities in order
     * @param entities Entities
     * @param <T> Entity class reference
     * @param <I> Identifier class
     */
    <T extends IdentifiedEntity<I>, I extends Comparable<I>> void lock(Collection<T> entities);

    /**
     * Save entity
     * @param entity Entity
     * @param <T> Entity class reference
     * @param <I> Identifier class
     */
    <T extends IdentifiedEntity<I>, I> void save(T entity);

    /**
     * Delete by identifier
     * @param clazz Entity class reference
     * @param id identifier
     * @param <T> Entity class
     * @param <I> Identifier class
     */
    <T extends IdentifiedEntity<I>, I> void deleteById(Class<T> clazz, I id);

    /**
     * Delete entity
     * @param entity Entity
     * @param <T> Entity class
     * @param <I> Identifier class
     */
    <T extends IdentifiedEntity<I>, I> void delete(T entity);

    /**
     * Flush all pending changes
     */
    void flush();

}
