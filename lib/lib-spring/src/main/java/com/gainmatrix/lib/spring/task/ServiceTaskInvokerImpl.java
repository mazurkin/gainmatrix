package com.gainmatrix.lib.spring.task;

import com.gainmatrix.lib.business.exception.AbstractServiceException;
import com.gainmatrix.lib.spring.security.authority.AuthorityExecutor;
import com.gainmatrix.lib.task.ServiceTask;
import com.gainmatrix.lib.task.ServiceTaskInvoker;
import com.gainmatrix.lib.task.ServiceTaskInvokerException;
import com.gainmatrix.lib.time.Chronometer;
import com.gainmatrix.lib.time.ChronometerTimer;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Запуск команд через контекст Spring Framework
 */
public class ServiceTaskInvokerImpl implements ServiceTaskInvoker {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTaskInvokerImpl.class);

    private ApplicationContext applicationContext;

    private AuthorityExecutor authorityExecutor;

    private Chronometer chronometer;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(applicationContext, "Application context is not set");
    }

    @Override
    public Set<String> getNames() {
        String[] names = applicationContext.getBeanNamesForType(ServiceTask.class);

        Set<String> result = new HashSet<String>(names.length);
        Collections.addAll(result, names);

        return result;
    }

    @Override
    public void execute(String name) throws ServiceTaskInvokerException {
        Preconditions.checkNotNull(name, "Job name is not set");

        LOGGER.debug("About to execute job with name [{}]", name);

        ServiceTask<?> task;

        try {
            task = applicationContext.getBean(name, ServiceTask.class);
        } catch (NoSuchBeanDefinitionException e) {
            throw new ServiceTaskInvokerException("Bean [" + name + "] is not found", e);
        } catch (BeanNotOfRequiredTypeException e) {
            throw new ServiceTaskInvokerException("Bean [" + name + "] is not of ServiceTask class", e);
        } catch (Exception e) {
            throw new ServiceTaskInvokerException("Fail to get bean [" + name + "]", e);
        }

        ChronometerTimer timer = new ChronometerTimer(chronometer);

        try {
            authorityExecutor.execute(task);
        } catch (AbstractServiceException e) {
            throw new ServiceTaskInvokerException("Service exception for task [" + name + "]", e);
        }

        LOGGER.debug("Job with name [{}] executed in {} ms", name, timer.elapsed());
    }

    @Resource
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Required
    public void setAuthorityExecutor(AuthorityExecutor authorityExecutor) {
        this.authorityExecutor = authorityExecutor;
    }

    @Required
    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

}
