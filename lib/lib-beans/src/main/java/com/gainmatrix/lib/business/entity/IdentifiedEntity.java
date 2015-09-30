package com.gainmatrix.lib.business.entity;

import com.gainmatrix.lib.beans.Identified;

/**
 * Идентифицируемая сущность
 * @param <I> Тип идентификатора сущности
 */
public interface IdentifiedEntity<I> extends Identified<I> {

    /**
     * Запрос идентификатора сущности в базе данных
     * @return Идентификатор сущности в базе данных либо null, если сущность еще не сохранена
     */
    @Override
    I getId();

}
