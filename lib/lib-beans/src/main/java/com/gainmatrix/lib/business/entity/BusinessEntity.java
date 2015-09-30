package com.gainmatrix.lib.business.entity;

/**
 * Типовая абстракция сущности с бизнес-идентификатором
 * @param <I> Тип идентификатора сущности
 */
public interface BusinessEntity<I> extends IdentifiedEntity<I> {

    /**
     * Запрос бизнес-идентификатора
     * @return Бизнес-идентификатор
     * @see BusinessId
     */
    BusinessId getBusinessId();

    /**
     * Запрос версии бизнес-модели которая создала эту сущность
     * @return Версия модели
     */
    int getBusinessModelVersion();

    /**
     * Запрос текущей версии изменений сущности
     * @return Версия сущности
     */
    int getVersion();

}
