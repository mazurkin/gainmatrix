package com.gainmatrix.lib.jmx;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.management.remote.JMXAuthenticator;
import javax.security.auth.Subject;

public class SimpleJMXAuthenticator implements JMXAuthenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleJMXAuthenticator.class);

    private String login;

    private String password;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(login, "JMX login is not set");
        Preconditions.checkNotNull(password, "JMX password is not set");
    }

    @Override
    public Subject authenticate(Object credentials) {
        // Проверка параметров аутентификации
        if (credentials == null) {
            throw new SecurityException("Credentials are null");
        }

        if (!(credentials instanceof String[])) {
            throw new SecurityException("Wrong credentials class");
        }

        String[] authParameters = (String[]) credentials;

        if (authParameters.length != 2) {
            throw new SecurityException("Credentials arrays must have just two elements");
        }

        // Проверка параметров
        String authLogin = authParameters[0];
        String authPassword = authParameters[1];

        LOGGER.info("JMX authentication with login: {}", authLogin);

        if (login.equals(authLogin) && password.equals(authPassword)) {
            return new Subject();
        }

        // Something wrong
        throw new SecurityException("Authentication failed");
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
