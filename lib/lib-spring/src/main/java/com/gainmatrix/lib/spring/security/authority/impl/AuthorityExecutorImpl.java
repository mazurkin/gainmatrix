package com.gainmatrix.lib.spring.security.authority.impl;

import com.gainmatrix.lib.business.exception.AbstractServiceException;
import com.gainmatrix.lib.spring.security.authority.AuthorityExecutor;
import com.gainmatrix.lib.task.ServiceTask;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс выполняющий указанный код под особыми привелегиями
 */
public class AuthorityExecutorImpl implements AuthorityExecutor {

    private static final String AUTHENTICATION_PRINCIPAL = "runtime";

    private static final String AUTHENTICATION_CREDENTIALS = "none";

    @NotNull
    private String key;

    @NotNull
    private String[] authorities;

    @NotNull
    private String principal;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(key, "RunAs key is null");
        Preconditions.checkNotNull(authorities, "Authorities are null");
        Preconditions.checkNotNull(principal, "Principal is null");
    }

    @Override
    public <T> T execute(ServiceTask<T> task) throws AbstractServiceException {
        Preconditions.checkNotNull(task, "Task is not set");

        SecurityContext context = SecurityContextHolder.getContext();

        Authentication originalAuthentication = context.getAuthentication();

        // Предлагаемые роли
        List<GrantedAuthority> proposedAuthorities = new LinkedList<GrantedAuthority>();
        for (String authority : authorities) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
            proposedAuthorities.add(grantedAuthority);
        }

        // Предлагаемая аутентификация
        Authentication proposedAuthentication = new RunAsUserToken(
                key,
                AUTHENTICATION_PRINCIPAL, AUTHENTICATION_CREDENTIALS,
                proposedAuthorities,
                AuthorityExecutorAuthentication.class
        );

        // Выполнение кода под предлангаемой аутентификаций с последующим восстановлением оригинальной аутентификации
        context.setAuthentication(proposedAuthentication);
        try {
            return task.execute();
        } finally {
            context.setAuthentication(originalAuthentication);
        }
    }

    /**
     * Установка ключа
     * @param key Ключ RunAs сервиса
     */
    @Required
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Установка авторизации на особое выполнение
     * @param authorities Авторизация (роль)
     */
    @Required
    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    /**
     * Установка имени пользователя на особое выполнение
     * @param principal Имя пользователя
     */
    @Required
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

}
