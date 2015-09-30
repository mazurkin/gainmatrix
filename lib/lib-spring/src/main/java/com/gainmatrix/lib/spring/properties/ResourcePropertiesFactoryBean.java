package com.gainmatrix.lib.spring.properties;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Factory to load properties from any resource. Allow to specify load mode (optional or mandatory)
 * <p>
 * Interface org.springframework.beans.factory.InitializingBean is used instead @PostConstruct to allow bean to be
 * initialized as soon as possible
 * @see org.springframework.beans.factory.InitializingBean
 */
public class ResourcePropertiesFactoryBean implements FactoryBean<Properties>, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePropertiesFactoryBean.class);

    private Properties properties;

    private Resource resource;

    private ResourcePropertiesFactoryMode mode = ResourcePropertiesFactoryMode.MANDATORY;

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(resource, "Resource is not set");
        Preconditions.checkNotNull(mode, "Mode is not set");

        properties = new Properties();

        if (resource.exists()) {
            InputStream inputStream = resource.getInputStream();
            try {
                properties.load(inputStream);
            } finally {
                inputStream.close();
            }
        } else {
            switch (mode) {
                case MANDATORY:
                    throw new IOException("Specified resource doesn't exist [" + resource.toString() + "]");
                case WARNING:
                    LOGGER.warn("Specified resource doesn't exist [{}]", resource.toString());
                    break;
                default:
                    LOGGER.debug("Specified resource doesn't exist [{}]", resource.toString());
                    break;
            }
        }
    }

    @Override
    public Properties getObject() throws Exception {
        return properties;
    }

    @Override
    public Class<?> getObjectType() {
        return Properties.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setMode(ResourcePropertiesFactoryMode mode) {
        this.mode = mode;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
