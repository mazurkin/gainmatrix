package com.gainmatrix.lib.spring.properties;

/**
 * Mode to load properties in
 */
public enum ResourcePropertiesFactoryMode {

    /**
     * Resource must exist
     */
    MANDATORY,

    /**
     * Warning will be printed in logger if resource doesn't exist
     */
    WARNING,

    /**
     * Do nothing if resource doesn't exist
     */
    OPTIONAL

}
