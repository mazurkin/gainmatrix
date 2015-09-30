package com.gainmatrix.lib.spring.properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;
import java.util.Properties;

/**
 * Adapter java.util.Map to java.util.Properties which allows to specify null values in maps which results to no
 * property set in java.util.Properties
 * <p>
 * Interface org.springframework.beans.factory.InitializingBean is used instead @PostConstruct to allow bean to be
 * initialized as soon as possible
 * @see org.springframework.beans.factory.InitializingBean
 */
public class MapPropertiesFactoryBean implements FactoryBean<Properties>, InitializingBean {

    private Map<Object, Object> map;

    private Properties properties;

    @Override
    public void afterPropertiesSet() throws Exception {
        properties = new Properties();

        if (map != null) {
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    properties.put(key, value);
                }
            }
        }
    }

    @Override
    public Properties getObject() throws Exception {
        return properties;
    }

    @Override
    public Class<Properties> getObjectType() {
        return Properties.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

}
