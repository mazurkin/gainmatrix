package com.gainmatrix.lib.thread.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class ThreadUncaughtExceptionHandlerIninitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUncaughtExceptionHandlerIninitializer.class);

    private Thread.UncaughtExceptionHandler exceptionHandler;

    @PostConstruct
    public void initialize() throws Exception {
        LOGGER.info("Uncaught thread exception handler is set");

        Thread.setDefaultUncaughtExceptionHandler(this.exceptionHandler);
    }

    public void setExceptionHandler(Thread.UncaughtExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

}
