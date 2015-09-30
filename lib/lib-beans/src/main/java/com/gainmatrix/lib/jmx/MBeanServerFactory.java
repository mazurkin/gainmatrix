package com.gainmatrix.lib.jmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.MBeanServerDelegate;

import java.lang.management.ManagementFactory;

/**
 * Наша собственная фабрика для запроса экземпляра MBeanFactory.<p/>
 *
 * Под Tomcat и Jboss мы наблюдаем целых два MBean сервера - собственный и платформенный -
 * Spring Framework автоматически выбирает первый (собственный) - а вот jConsole локально показывает только
 * платформенный.<p/>
 *
 * Данный бин принудительно выбирает платформенный сервер
 */
public class MBeanServerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(MBeanServerFactory.class);

    private MBeanServer server;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        // Запрашиваем платформенный MBeanServer
        server = ManagementFactory.getPlatformMBeanServer();
        // Выводим информацию о выбранном сервере
        String serverName = (String) server.getAttribute(MBeanServerDelegate.DELEGATE_NAME, "MBeanServerId");
        LOGGER.info("MBeanServer [{}] is selected", serverName);
    }

    /**
     * Запрос экземпляра MBeanServer, который будет использоваться для регистрации JMX-бинов в приложении
     * @return Сервер
     */
    public MBeanServer getServer() {
        return server;
    }

}
