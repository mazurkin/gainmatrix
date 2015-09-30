package com.gainmatrix.lib.jpa;

import com.gainmatrix.lib.beans.IdentifiedComparator;
import com.gainmatrix.lib.business.entity.BusinessEntity;
import com.gainmatrix.lib.business.entity.BusinessEntityDao;
import com.gainmatrix.lib.business.entity.BusinessId;
import com.gainmatrix.lib.business.entity.IdentifiedEntity;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JPA implementation of {@link com.gainmatrix.lib.business.entity.BusinessEntityDao}
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class BusinessEntityJpaDao implements BusinessEntityDao {

    private static final String ENTITY_ID = "id";

    private static final String ENTITY_BUSINESS_ID = "businessId";

    private static final String ENTITY_BUSINESS_ID_HI = "hi";

    private static final String ENTITY_BUSINESS_ID_LO = "lo";

    private EntityManager entityManager;

    @Override
    public <T extends IdentifiedEntity<I>, I> T findById(Class<T> clazz, I id) {
        return entityManager.find(clazz, id, LockModeType.NONE);
    }

    @Override
    public <T extends IdentifiedEntity<I>, I> Set<T> findByIds(Class<T> clazz, Collection<I> ids) {
        if ((ids == null) || (ids.isEmpty())) {
            return Collections.emptySet();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);

        // CHECKSTYLE-OFF: NestedMethodCall
        criteriaQuery.where(
            root.get(ENTITY_ID).in(ids)
        );
        // CHECKSTYLE-ON: NestedMethodCall

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);

        List<T> resultList = typedQuery.getResultList();
        Set<T> resultSet = new HashSet<T>(resultList);

        return Collections.unmodifiableSet(resultSet);
    }

    @Override
    public <T extends BusinessEntity<I>, I> T findByBusinessId(Class<T> clazz, BusinessId businessId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);

        // CHECKSTYLE-OFF: NestedMethodCall
        criteriaQuery.where(
            criteriaBuilder.and(
                criteriaBuilder.equal(
                    root.<BusinessId>get(ENTITY_BUSINESS_ID).get(ENTITY_BUSINESS_ID_HI),
                    businessId.getHi()
                ),
                criteriaBuilder.equal(
                    root.<BusinessId>get(ENTITY_BUSINESS_ID).get(ENTITY_BUSINESS_ID_LO),
                    businessId.getLo()
                )
            )
        );
        // CHECKSTYLE-ON: NestedMethodCall

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public <T extends IdentifiedEntity<I>, I> T lockById(Class<T> clazz, I id) {
        prepateLock();
        return entityManager.find(clazz, id, LockModeType.PESSIMISTIC_WRITE);
    }

    @Override
    public <T extends IdentifiedEntity<I>, I extends Comparable<I>> Set<T> lockById(Class<T> clazz, Collection<I> ids) {
        if ((ids == null) || (ids.isEmpty())) {
            return Collections.emptySet();
        }

        List<I> idsList = new ArrayList<I>(ids);
        Collections.sort(idsList);

        Set<T> result = new HashSet<T>(idsList.size());

        prepateLock();

        for (I id : idsList) {
            T lockedEntity = entityManager.find(clazz, id, LockModeType.PESSIMISTIC_WRITE);
            result.add(lockedEntity);
        }

        return result;
    }

    @Override
    public <T extends IdentifiedEntity<I>, I> void lock(T entity) {
        if (entity != null) {
            prepateLock();
            entityManager.lock(entity, LockModeType.PESSIMISTIC_WRITE);
        }
    }

    @Override
    public <T extends IdentifiedEntity<I>, I extends Comparable<I>> void lock(Collection<T> entities) {
        if ((entities == null) || (entities.isEmpty())) {
            return;
        }

        List<T> entitiesList = new ArrayList<T>(entities);
        Collections.sort(entitiesList, new IdentifiedComparator<T, I>());

        prepateLock();

        for (T entity : entitiesList) {
            entityManager.lock(entity, LockModeType.PESSIMISTIC_WRITE);
        }
    }

    private void prepateLock() {
        // We have to flush all pending changes before lock - otherwise Hibernate doesn't see an unsaved entity
        // and doesn't perform automatic flushing
        // https://hibernate.onjira.com/browse/HHH-4177
        entityManager.flush();
    }

    @Override
    public <T extends IdentifiedEntity<I>, I> void save(T entity) {
        Preconditions.checkNotNull(entity, "Entity is null");
        if (!entityManager.contains(entity)) {
            entityManager.persist(entity);
        }
    }

    @Override
    public <T extends IdentifiedEntity<I>, I> void deleteById(Class<T> clazz, I id) {
        T entity = findById(clazz, id);

        if (entity != null) {
            delete(entity);
        }
    }

    @Override
    public <T extends IdentifiedEntity<I>, I> void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
