package com.gainmatrix.lib.spring.jmx;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

import javax.management.JMException;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.rmi.RMIConnectorServer;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Фабрика по созданию JMX-сервера программным путем. Основана на спринговском бине ConnectorServerFactoryBean,
 * функционал которого расширен добавлением возможности указания файлов с паролями и правами доступа, а также фиксацией
 * порта на котором будут слушать соединение RMIServer и RMIConnection, что позволяет обойти проблему с подключением к
 * JMX сервера за брандмауэром.
 * <p/>
 * Строчка для соединения в консоли будет выглядеть следующим образом:
 * service:jmx:rmi://<b>domain</b>:<b>objport</b>/jndi/rmi://<b>domain</b>:<b>regport</b>/server
 * <p/>
 * <a href="http://java.sun.com/javase/6/docs/technotes/guides/management/agent.html">
 *     Создание программного JMX-агента</a><br/>
 * <a href="http://blogs.sun.com/jmxetc/entry/troubleshooting_connection_problems_in_jconsole">
 *     Проблемы с jConsole</a><br/>
 * <a href="http://blogs.sun.com/lmalventosa/entry/mimicking_the_out_of_the">
 *     Mimicking the out-of-the-box JMX management agent</a>
 *
 * @see ConnectorServerFactoryBean
 */
public class CustomConnectorServerFactoryBean extends ConnectorServerFactoryBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomConnectorServerFactoryBean.class);

    private boolean useSSL;

    private int registryPort;

    private String registryHost;

    private int objectsPort;

    private String objectsHost;

    private JMXAuthenticator authenticator;

    @Override
    public void afterPropertiesSet() throws JMException, IOException {
        Preconditions.checkArgument(registryPort > 0, "Registry port is not set");
        Preconditions.checkArgument(objectsPort > 0, "Objects port is not set");
        Preconditions.checkNotNull(authenticator, "Authenticator is null");

        if (registryHost == null) {
            registryHost = InetAddress.getLocalHost().getHostName();
        }

        if (objectsHost == null) {
            objectsHost = InetAddress.getLocalHost().getHostName();
        }

        String url = String.format("service:jmx:rmi://%1$s:%2$d/jndi/rmi://%3$s:%4$d/server",
                objectsHost,
                objectsPort,
                registryHost,
                registryPort
        );
        super.setServiceUrl(url);

        Map<String, Object> envMap = new HashMap<String, Object>();

        envMap.put(JMXConnectorServer.AUTHENTICATOR, authenticator);

        if (useSSL) {
            SslRMIClientSocketFactory csf = new SslRMIClientSocketFactory();
            envMap.put(RMIConnectorServer.RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE, csf);
            SslRMIServerSocketFactory ssf = new SslRMIServerSocketFactory();
            envMap.put(RMIConnectorServer.RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE, ssf);
        }

        super.setEnvironmentMap(envMap);

        LOGGER.info("JMX service url : {}", url);

        super.afterPropertiesSet();
    }

    /**
     * Установка номера порта для RMI Registry
     * @param registryPort Номер порта
     */
    @Required
    public void setRegistryPort(int registryPort) {
        this.registryPort = registryPort;
    }

    /**
     * Установка номера порта для RMIServer и RMIConnection
     * @param objectsPort Номер порта
     */
    @Required
    public void setObjectsPort(int objectsPort) {
        this.objectsPort = objectsPort;
    }

    /**
     * Использовать ли SSL при подключении
     * @param useSSL Флаг использования SSL
     */
    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    /**
     * Установка имени хоста. Если имя хоста не устанавливается, то оно будет запрошено у системы.
     * @param registryHost Имя хоста
     */
    public void setRegistryHost(String registryHost) {
        this.registryHost = registryHost;
    }

    public void setObjectsHost(String objectsHost) {
        this.objectsHost = objectsHost;
    }

    @Required
    public void setAuthenticator(JMXAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public void setServiceUrl(String serviceUrl) {
        throw new UnsupportedOperationException("This setter was hidden");
    }

    @Override
    public void setEnvironmentMap(Map environment) {
        throw new UnsupportedOperationException("This setter was hidden");
    }

    @Override
    public void setEnvironment(Properties environment) {
        throw new UnsupportedOperationException("This setter was hidden");
    }

}
