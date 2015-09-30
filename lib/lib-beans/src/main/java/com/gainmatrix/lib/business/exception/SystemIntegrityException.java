package com.gainmatrix.lib.business.exception;

/**
 * Исключение сигнализирующее о проблеме взаимодействия внутри системы
 */
public class SystemIntegrityException extends RuntimeException {

    public SystemIntegrityException(String msg) {
        super(msg);
    }

    public SystemIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
