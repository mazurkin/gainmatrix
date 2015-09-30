package com.gainmatrix.lib.beans;

/**
 * Интерфейс декларирующий обладание уникальным идентификатором
 * @param <I> Тип идентификатора
 */
public interface Identified<I> {

    /**
     * Запрос идентификатора
     * @return Идентификатор
     */
    I getId();

}
