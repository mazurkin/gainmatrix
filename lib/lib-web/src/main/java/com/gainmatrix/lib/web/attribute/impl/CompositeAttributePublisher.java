package com.gainmatrix.lib.web.attribute.impl;

import com.gainmatrix.lib.web.attribute.AttributePublisher;
import com.google.common.base.Preconditions;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import java.util.Collection;

/**
 * Композитный публикатор аттрибутов
 */
public class CompositeAttributePublisher implements AttributePublisher {

    private Collection<AttributePublisher> attributePublishers;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(attributePublishers, "Publisher list is not set");
    }

    @Override
    public void publish(HttpServletRequest request) {
        for (AttributePublisher attributePublisher : attributePublishers) {
            attributePublisher.publish(request);
        }
    }

    public void setAttributePublishers(Collection<AttributePublisher> attributePublishers) {
        this.attributePublishers = attributePublishers;
    }

}
