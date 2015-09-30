package com.gainmatrix.lib.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Static helper utility to run the application bootstrap from the Java main() function. Class translates exceptions
 * into exit code
 * @see ApplicationBootstrap
 */
public final class ApplicationBootstrapExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationBootstrapExecutor.class);

    private static final int EXIT_CODE_ERROR = -1;

    private ApplicationBootstrapExecutor() {
    }

    /**
     * Execute standart application init-run-destroy cycle
     * @param arguments Application arguments
     * @param instance Application instance
     * @return Exit code from the ApplicationBootstrap instance. Also return -1 on any internal error or exception.
     * @see ApplicationBootstrap
     */
    public static int execute(String[] arguments, ApplicationBootstrap instance) {
        // Check is application instance not null
        if (instance == null) {
            LOGGER.error("Application instance is null");
            return EXIT_CODE_ERROR;
        }

        // Init, execute and destroy the application instance
        try {
            instance.init(arguments);
        } catch (Exception e) {
            String argumentsText = Arrays.toString(arguments);
            LOGGER.error("Fail to initialize application with arguments: " + argumentsText, e);
            return EXIT_CODE_ERROR;
        }

        try {
            return instance.run();
        } catch (Exception e) {
            LOGGER.error("Fail to execute application", e);
            return EXIT_CODE_ERROR;
        } finally {
            instance.destroy();
        }
    }

}
