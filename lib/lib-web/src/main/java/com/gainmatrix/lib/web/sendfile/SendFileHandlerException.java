package com.gainmatrix.lib.web.sendfile;

public class SendFileHandlerException extends Exception {

    public SendFileHandlerException() {
    }

    public SendFileHandlerException(String message) {
        super(message);
    }

    public SendFileHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendFileHandlerException(Throwable cause) {
        super(cause);
    }
}
