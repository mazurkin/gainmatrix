package com.gainmatrix.lib.thread.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadUncaughtExceptionLoggerHandler implements Thread.UncaughtExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUncaughtExceptionLoggerHandler.class);

    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        LOGGER.error("Uncaught exception is found for thread [" + thread + "]", exception);
    }

}
