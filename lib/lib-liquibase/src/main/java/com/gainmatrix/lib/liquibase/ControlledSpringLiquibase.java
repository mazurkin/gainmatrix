package com.gainmatrix.lib.liquibase;

import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Liquibase initializer with 'enabled' switch
 */
public class ControlledSpringLiquibase extends SpringLiquibase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControlledSpringLiquibase.class);

    private boolean enabled = false;

    @Override
    public void afterPropertiesSet() throws LiquibaseException {
        LOGGER.info("Liquibase update is {}", enabled ? "active" : "disabled");

        String enabledAsString = Boolean.toString(enabled);

        System.setProperty(Liquibase.SHOULD_RUN_SYSTEM_PROPERTY, enabledAsString);
        try {
            super.afterPropertiesSet();
        } finally {
            System.clearProperty(Liquibase.SHOULD_RUN_SYSTEM_PROPERTY);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
