package com.gainmatrix.lib.task;

import java.util.Set;

/**
 * Интерфейс к исполнителю команд
 */
public interface ServiceTaskInvoker {

    /**
     * Запрос списка имен всех команд
     * @return Список имен
     */
    Set<String> getNames();

    /**
     * Выполнение команды по ее имени
     * @param name Имя команды
     * @throws ServiceTaskInvokerException Исключение в случае ошибки
     */
    void execute(String name) throws ServiceTaskInvokerException;

}
