package com.gainmatrix.lib.task;

import com.gainmatrix.lib.business.exception.AbstractServiceException;

/**
 * Абстрактное определения команды-обработчика - сервиса с одним методом без аргументов,
 * который полностью сам в курсе что и как должно быть выполнено. Применяется в основном
 * для реализации задач, автоматически запускаемых планировщиком в определенные моменты
 * времени.
 * @param <T> Type of return result
 */
public interface ServiceTask<T> {

    /**
     * Выполнение бизнес задачи
     * @return Результат
     * @throws AbstractServiceException Исключение в случае ошибки выполнения
     */
    T execute() throws AbstractServiceException;

}
