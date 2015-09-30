package com.gainmatrix.lib.business.entity;

/**
 * Abstract DAO for a business entity
 */
public interface BusinessEntityDao extends IdentifiedEntityDao {

    /**
     * Load entity by business identifier
     * @param clazz Entity class instance
     * @param businessId Business identifier
     * @param <T> Entity class
     * @param <I> Identifier class
     * @return Entity
     */
    <T extends BusinessEntity<I>, I> T findByBusinessId(Class<T> clazz, BusinessId businessId);

}
