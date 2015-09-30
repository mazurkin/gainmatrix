package com.gainmatrix.lib.spring.security.password;

/**
 * Шифровальщик пользовательского пароля
 */
public interface UserPasswordCipher {

    /**
     * Шифрование пароля (обычно для последующего сохранения в БД)
     * @param username Имя пользователя
     * @param plainPassword Незашифрованный пароль
     * @return Зашифрованный пароль
     */
    String cipher(String username, String plainPassword);

}
