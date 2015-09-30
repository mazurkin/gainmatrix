package com.gainmatrix.lib.spring.application;

import com.gainmatrix.lib.application.ApplicationBootstrap;
import com.gainmatrix.lib.task.ServiceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper for SpringContextApplication implementing ApplicationBootstrap interface
 * @see ApplicationBootstrap
 */
public class SpringContextApplicationBootstrap implements ApplicationBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextApplicationBootstrap.class);

    private static final int EXIT_CODE_SUCCESS = 0;

    private final SpringContextApplication springContextApplication;

    private final String taskBeanName;

    public SpringContextApplicationBootstrap(SpringContextApplication springContextApplication, String taskBeanName) {
        this.springContextApplication = springContextApplication;
        this.taskBeanName = taskBeanName;
    }

    @Override
    public void init(String[] arguments) throws Exception {
        springContextApplication.start();
    }

    @Override
    public void destroy() {
        try {
            springContextApplication.shutdown();
        } catch (Exception e) {
            LOGGER.error("Fail to shutdown contexts", e);
        }
    }

    @Override
    public int run() throws Exception {
        // Search for the task by it's name
        ServiceTask<?> task = springContextApplication.getServiceTask(taskBeanName);

        // Execute the task
        task.execute();

        // No exception means that everything is fine
        return EXIT_CODE_SUCCESS;
    }
}
