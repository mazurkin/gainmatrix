package com.gainmatrix.lib.business.exception;

/**
 * Исключение информирующее о том, что сущность с искомым идентификатором не найдена в базе данных.
 */
public class MissingEntityException extends RuntimeException {

    private final Class<?> clazz;

    private final Object id;

    /**
     * Создание исключения
     * @param clazz Класс сущности
     * @param id Идентификатор сущности
     */
    public MissingEntityException(Class<?> clazz, Object id) {
        super(String.format("Missing entity with class=[%s] and id=[%s]", clazz.getName(), id.toString()));
        this.clazz = clazz;
        this.id = id;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Object getId() {
        return id;
    }

}
