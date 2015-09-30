package com.gainmatrix.lib.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Callable proxy which could retry inner command in case of exception
 * @param <T> Result type
 */
public class RetrialCallable<T> implements Callable<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetrialCallable.class);

    private final int trials;

    private final Callable<T> command;

    private final Class<? extends Exception> exceptionClass;

    /**
     * Create callable wrapper
     * @param command Callable to execute
     * @param trials Number of trials
     */
    public RetrialCallable(Callable<T> command, int trials) {
        this(command, trials, null);
    }

    /**
     * Create callable wrapper
     * @param command Callable to execute
     * @param trials Number of trials
     * @param exceptionClass If this exception occurs proxy will try to execute the command again. Specify null for all
     * exceptions
     */
    public RetrialCallable(Callable<T> command, int trials, Class<? extends Exception> exceptionClass) {
        this.trials = trials;
        this.command = command;
        this.exceptionClass = exceptionClass;
    }

    public T call() throws Exception {
        for (int trial = 1; true; trial++) {
            try {
                return command.call();
            } catch (Exception e) {
                handleFailure(trial, e);
            }
        }
    }

    /**
     * Error handler. Subclass could override this method and add some custom logic (for example sleep pause)
     * @param trial Trial index (started with 1)
     * @param e Exception occured
     * @throws Exception Exception in case the number of retrials exceeded the limit
     */
    protected void handleFailure(int trial, Exception e) throws Exception {
        LOGGER.debug("Trial is failed with exception", e);

        if ((exceptionClass == null) || exceptionClass.isAssignableFrom(e.getClass())) {
            if (trial >= trials) {
                throw e;
            }
        } else {
            throw e;
        }
    }

}
