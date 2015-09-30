package com.gainmatrix.lib.spring.security.password;

/**
 * Источник криптографической "соли" с использованием параметров пользователя
 */
public interface UserSaltSource {

    /**
     * Запрос криптографической соли
     * @param username Имя пользователя
     * @return Соль
     */
    String getSalt(String username);

}
