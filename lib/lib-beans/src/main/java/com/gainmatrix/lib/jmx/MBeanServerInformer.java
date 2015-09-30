package com.gainmatrix.lib.jmx;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.MBeanServerDelegate;
import javax.management.MBeanServerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Бин выводит в лог количество найденных MBean серверов и их имена
 */
public class MBeanServerInformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MBeanServerInformer.class);

    private String agendId = null;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        List<MBeanServer> servers = MBeanServerFactory.findMBeanServer(agendId);

        if (LOGGER.isInfoEnabled()) {
            if (CollectionUtils.isNotEmpty(servers)) {
                List<String> names = new LinkedList<String>();

                for (MBeanServer server : servers) {
                    String serverName = (String) server.getAttribute(
                        MBeanServerDelegate.DELEGATE_NAME, "MBeanServerId");
                    names.add(serverName);
                }

                LOGGER.info("Found {} MBeanServer(s) with agent-id '{}' : {}",
                        new Object[] {servers.size(), agendId, names}
                );
            } else {
                LOGGER.info("MBeanServer(s) with agent-id '{}' aren't found", agendId);
            }
        }
    }

    public void setAgendId(String agendId) {
        this.agendId = agendId;
    }

}
