package com.gainmatrix.lib.spring.security.authority;

import com.gainmatrix.lib.business.exception.AbstractServiceException;
import com.gainmatrix.lib.task.ServiceTask;

/**
 * Авторизационный контекст
 */
public interface AuthorityExecutor {

    /**
     * Выполнение указанного кода под особыми привилегиями
     * @param task Код которые необходимо выполнить
     * @param <T> Тип результата
     * @return Результат выполнения кода
     * @throws AbstractServiceException Исключение в случае ошибки выполнения кода
     */
    <T> T execute(ServiceTask<T> task) throws AbstractServiceException;

}
