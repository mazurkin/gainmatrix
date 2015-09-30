package com.gainmatrix.lib.reflection;

public class BeanReflectionMismatchException extends RuntimeException {

    public BeanReflectionMismatchException() {
    }

    public BeanReflectionMismatchException(String message) {
        super(message);
    }

    public BeanReflectionMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanReflectionMismatchException(Throwable cause) {
        super(cause);
    }

}
