package com.gainmatrix.lib.rmi;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LocateRegistryFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocateRegistryFactory.class);

    private int port;

    private Registry registry;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(port > 0, "Port is not set");

        registry = LocateRegistry.createRegistry(port);
        Preconditions.checkNotNull(registry, "Registry is not created");

        LOGGER.info("RMI registry is created on port {}", port);
    }

    public Registry getRegistry() {
        return registry;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
