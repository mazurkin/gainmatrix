package com.gainmatrix.lib.beans;

/**
 * Абстрактный конвертор преобразующий бин одного типа в бин другого типа
 * @param <S> Тип исходного бина
 * @param <T> Тип результатирующего бина
 */
public interface Convertor<S, T> {

    /**
     * Преобразование бина одного типа в бин другого типа
     * @param object Исходный бин
     * @return Сконвертированный бин
     */
    T convert(S object);

}
