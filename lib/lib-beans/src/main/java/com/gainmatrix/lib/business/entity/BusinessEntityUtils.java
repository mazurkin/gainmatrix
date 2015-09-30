package com.gainmatrix.lib.business.entity;

/**
 * Набор вспомогательных статических функций для работы с сущностями
 */
public final class BusinessEntityUtils {

    private BusinessEntityUtils() {
    }

    /**
     * Обновление бизнес-идентификатора сущности. Установка свойства бизнес-идентификатор запрещена в классе
     * AbstractBusinessEntity (модификатор метода protected), однако для тестов такую возможность необходимо иметь.
     * @param entity Сущность
     * @param businessId Новый бизнес-идентификатор
     * @param <T> Тип сущности
     * @see AbstractBusinessEntity
     */
    public static <T extends AbstractBusinessEntity> void updateBusinessId(T entity, BusinessId businessId) {
        entity.setBusinessId(businessId);
    }

    /**
     * Обновление версии бизнес-модели сущности. Установка свойства версии бизнес-модели запрещена в классе
     * AbstractBusinessEntity (модификатор метода protected), однако для тестов такую возможность необходимо иметь.
     * @param entity Сущность
     * @param businessModelVersion Версия бизнес-модели
     * @param <T> Тип сущности
     * @see AbstractBusinessEntity
     */
    public static <T extends AbstractBusinessEntity> void updateBusinessModelVersion(
        T entity,
        int businessModelVersion)
    {
        entity.setBusinessModelVersion(businessModelVersion);
    }

    /**
     * Обновление версии сущности. Установка свойства версии сущности запрещена в классе
     * AbstractBusinessEntity (модификатор метода protected), однако для тестов такую возможность необходимо иметь.
     * @param entity Сущность
     * @param version Версия сущности
     * @param <T> Тип сущности
     * @see AbstractBusinessEntity
     */
    public static <T extends AbstractBusinessEntity> void updateVersion(T entity, int version) {
        entity.setVersion(version);
    }

}
