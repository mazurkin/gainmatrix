package com.gainmatrix.lib.business.exception;

/**
 * Общий базовый класс для исключений слоя бизнес-сервисов. Все наследуемые исключение объединяет то, что они могут и
 * должны быть обработаны в контроллерах разумным способом - с выдачей осмысленного текстового сообщения пользователю в
 * том или ином виде.
 */
public abstract class AbstractServiceException extends Exception {

    public AbstractServiceException() {
    }

    public AbstractServiceException(Throwable cause) {
        super(cause);
    }

    public AbstractServiceException(String message) {
        super(message);
    }

    public AbstractServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
