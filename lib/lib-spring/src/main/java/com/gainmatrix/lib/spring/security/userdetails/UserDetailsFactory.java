package com.gainmatrix.lib.spring.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Фабрика выдающая описание пользователя в формате Spring Security на основании произвольной сущности
 * @param <T> Тип исходной сущности
 */
public interface UserDetailsFactory<T> {

    /**
     * Запрос описания пользователя
     * @param object Сущность
     * @return Описание пользователя
     */
    UserDetails getUserDetails(T object);

}
