package com.gainmatrix.lib.jpa;

import com.gainmatrix.lib.business.entity.BusinessEntity;
import com.gainmatrix.lib.business.entity.BusinessEntityDao;
import com.gainmatrix.lib.reflection.BeanReflectionUtils;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

public final class BusinessEntityJpaDaoChecker {

    private BusinessEntityJpaDaoChecker() {
    }

    /**
     * Автоматическая проверка типовых операций типового DAO
     * @param entityManager Сессия JPA
     * @param dao Реализация DAO
     * @param entity Инициализированная сущность
     * @param clazz Класс сущности
     * @param <T> Тип сущности
     * @param <I> Тип идентификатора сущности
     * @throws Exception Исключение в случае ошибки
     */
    public static <T extends BusinessEntity<I>, I> void checkBusinessEntityDao(
            EntityManager entityManager,
            BusinessEntityDao dao,
            Class<T> clazz,
            T entity) throws Exception
    {

        dao.save(entity);

        entityManager.flush();
        entityManager.clear();

        T loaded1 = dao.findById(clazz, entity.getId());
        boolean equal1 = entity.equals(loaded1) && loaded1.equals(entity);
        Assert.isTrue(equal1);
        BeanReflectionUtils.checkEqual(entity, loaded1, 1);
        BeanReflectionUtils.checkEqual(loaded1, entity, 1);

        entityManager.clear();

        T loaded2 = dao.findByBusinessId(clazz, entity.getBusinessId());
        boolean equal2 = entity.equals(loaded2) && loaded2.equals(entity);
        Assert.isTrue(equal2);
        BeanReflectionUtils.checkEqual(entity, loaded2, 1);
        BeanReflectionUtils.checkEqual(loaded2, entity, 1);

        entityManager.clear();

        T loaded3 = dao.lockById(clazz, entity.getId());
        boolean equal3 = entity.equals(loaded3) && loaded3.equals(entity);
        Assert.isTrue(equal3);
        BeanReflectionUtils.checkEqual(entity, loaded3, 1);
        BeanReflectionUtils.checkEqual(loaded3, entity, 1);

        entityManager.clear();

        dao.deleteById(clazz, entity.getId());

        entityManager.flush();
        entityManager.clear();

        T loaded4 = dao.findById(clazz, entity.getId());
        Assert.isNull(loaded4);
    }

}
