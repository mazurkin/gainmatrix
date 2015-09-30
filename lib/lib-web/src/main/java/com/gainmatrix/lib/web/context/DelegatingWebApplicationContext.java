package com.gainmatrix.lib.web.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

/**
 * Mock web context which delegates all call to the underlying context
 */
public class DelegatingWebApplicationContext implements WebApplicationContext {

    private final ApplicationContext applicationContext;

    private final ServletContext servletContext;

    public DelegatingWebApplicationContext(ApplicationContext applicationContext, ServletContext servletContext) {
        this.applicationContext = applicationContext;
        this.servletContext = servletContext;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public String getId() {
        return applicationContext.getId();
    }

    @Override
    public String getDisplayName() {
        return applicationContext.getDisplayName();
    }

    @Override
    public String getApplicationName() {
        return applicationContext.getApplicationName();
    }

    @Override
    public long getStartupDate() {
        return applicationContext.getStartupDate();
    }

    @Override
    public ApplicationContext getParent() {
        return applicationContext.getParent();
    }

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        return applicationContext.getAutowireCapableBeanFactory();
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return applicationContext.getParentBeanFactory();
    }

    @Override
    public boolean containsLocalBean(String s) {
        return applicationContext.containsLocalBean(s);
    }

    @Override
    public boolean containsBeanDefinition(String s) {
        return applicationContext.containsBeanDefinition(s);
    }

    @Override
    public int getBeanDefinitionCount() {
        return applicationContext.getBeanDefinitionCount();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return applicationContext.getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> aClass) {
        return applicationContext.getBeanNamesForType(aClass);
    }

    @Override
    public String[] getBeanNamesForType(Class<?> aClass, boolean b, boolean b1) {
        return applicationContext.getBeanNamesForType(aClass, b, b1);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> tClass) throws BeansException {
        return applicationContext.getBeansOfType(tClass);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> tClass, boolean b, boolean b1) throws BeansException {
        return applicationContext.getBeansOfType(tClass, b, b1);
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> aClass) throws BeansException {
        return applicationContext.getBeansWithAnnotation(aClass);
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String s, Class<A> aClass) {
        return applicationContext.findAnnotationOnBean(s, aClass);
    }

    @Override
    public Object getBean(String s) throws BeansException {
        return applicationContext.getBean(s);
    }

    @Override
    public <T> T getBean(String s, Class<T> tClass) throws BeansException {
        return applicationContext.getBean(s, tClass);
    }

    @Override
    public <T> T getBean(Class<T> tClass) throws BeansException {
        return applicationContext.getBean(tClass);
    }

    @Override
    public Object getBean(String s, Object... objects) throws BeansException {
        return applicationContext.getBean(s, objects);
    }

    @Override
    public boolean containsBean(String s) {
        return applicationContext.containsBean(s);
    }

    @Override
    public boolean isSingleton(String s) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(s);
    }

    @Override
    public boolean isPrototype(String s) throws NoSuchBeanDefinitionException {
        return applicationContext.isPrototype(s);
    }

    @Override
    public boolean isTypeMatch(String s, Class<?> aClass) throws NoSuchBeanDefinitionException {
        return applicationContext.isTypeMatch(s, aClass);
    }

    @Override
    public Class<?> getType(String s) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(s);
    }

    @Override
    public String[] getAliases(String s) {
        return applicationContext.getAliases(s);
    }

    @Override
    public Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return applicationContext.getMessage(code, args, defaultMessage, locale);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return applicationContext.getMessage(code, args, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return applicationContext.getMessage(resolvable, locale);
    }

    @Override
    public org.springframework.core.io.Resource[] getResources(String locationPattern) throws IOException {
        return applicationContext.getResources(locationPattern);
    }

    @Override
    public org.springframework.core.io.Resource getResource(String location) {
        return applicationContext.getResource(location);
    }

    @Override
    public ClassLoader getClassLoader() {
        return applicationContext.getClassLoader();
    }

}
