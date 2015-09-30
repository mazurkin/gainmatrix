package com.gainmatrix.lib.spring.application;

import com.gainmatrix.lib.task.ServiceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringContextApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextApplication.class);

    private final String[] locations;

    private ClassPathXmlApplicationContext applicationContext;

    public SpringContextApplication(String[] locations) {
        this.locations = locations;
    }

    /**
     * Запуск контекста приложения
     * @throws Exception Исключение в случае ошибки
     */
    public synchronized void start() throws Exception {
        if (applicationContext != null) {
            throw new IllegalStateException("Fail to start application - it's running");
        }

        if (LOGGER.isInfoEnabled()) {
            String locationsAsString = Arrays.toString(locations);
            LOGGER.info("Starting application with context locations {}", locationsAsString);
        }

        applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocations(locations);
        applicationContext.setAllowBeanDefinitionOverriding(true);
        applicationContext.setAllowCircularReferences(true);
        applicationContext.setValidating(true);
        applicationContext.registerShutdownHook();
        applicationContext.refresh();

        LOGGER.info("Application is started");
    }

    /**
     * Остановка контекста приложения
     * @throws Exception Исключение в случае ошибки
     */
    public synchronized void shutdown() throws Exception {
        if (applicationContext != null) {
            LOGGER.info("Closing the application");

            applicationContext.close();
            applicationContext = null;

            LOGGER.info("Application is closed");
        }
    }

    /**
     * Запрос сервиса-исполнителя по имени
     * @param name Имя сервиса
     * @return Сервис
     */
    public synchronized ServiceTask<?> getServiceTask(String name) {
        return applicationContext.getBean(name, ServiceTask.class);
    }

    /**
     * Запрос контекста приложения
     * @return Контекст
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
