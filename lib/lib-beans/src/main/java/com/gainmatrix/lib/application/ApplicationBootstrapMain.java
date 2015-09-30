package com.gainmatrix.lib.application;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements a "main" method which take the first argument as a ApplicationBootstrap class implementation and the rest
 * of original arguments as actual arguments
 */
public final class ApplicationBootstrapMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationBootstrapExecutor.class);

    private static final int EXIT_CODE_ERROR = -1;

    private ApplicationBootstrapMain() {
    }

    /**
     * Take an ApplicationBooststrap class as argument and runs it
     * @param arguments First argument is FQN of bootstrap class, other arguments are passed directly
     */
    public static void main(String[] arguments) {
        // Check the arguments
        if (ArrayUtils.isEmpty(arguments)) {
            LOGGER.error("No arguments specified");
            System.exit(EXIT_CODE_ERROR);
            return;
        }

        // Get the class from the first argument
        String className = arguments[0];
        Class<?> clazz;

        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Сlass is not found", e);
            System.exit(EXIT_CODE_ERROR);
            return;
        }

        if (!ApplicationBootstrap.class.isAssignableFrom(clazz)) {
            LOGGER.error("The class specified is not an implementation of ApplicationBootstrap");
            System.exit(EXIT_CODE_ERROR);
            return;
        }

        // Instantiate class
        ApplicationBootstrap instance;

        try {
            instance = (ApplicationBootstrap) clazz.newInstance();
        } catch (Exception e) {
            LOGGER.error("Fail to create a class instance", e);
            System.exit(EXIT_CODE_ERROR);
            return;
        }

        // Copy the rest of arguments (exclude the first argument)
        String[] actualArguments = (String[]) ArrayUtils.subarray(arguments, 1, arguments.length);

        // Execute ApplicationBootstrap instance
        int exitCode = ApplicationBootstrapExecutor.execute(actualArguments, instance);
        System.exit(exitCode);
    }

}
