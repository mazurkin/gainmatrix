package com.gainmatrix.lib.random;

/**
 * Генератор строк состоящих из случайных символов
 */
public interface RandomTokenGenerator {

    /**
     * Получить случайный токен
     * @param length Длина требуемого токена
     * @return Токен
     */
    String generate(int length);

}
