package com.gainmatrix.lib.freemarker.core;

import com.gainmatrix.lib.preconditions.Preconditions2;
import freemarker.cache.TemplateLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * Spring Context loader for FreeMarker loads resources from current Spring context
 * @see org.springframework.ui.freemarker.SpringTemplateLoader
 */
public class SpringContextTemplateLoader implements TemplateLoader, ResourceLoaderAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextTemplateLoader.class);

    private ResourceLoader resourceLoader;

    private Collection<String> templateLoaderPaths;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Preconditions2.checkNotEmpty(templateLoaderPaths, "Template loader paths is empty");
    }

    @Override
    public Object findTemplateSource(String name) throws IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Looking for FreeMarker template with name [" + name + "]");
        }

        for (String templateLoaderPath : templateLoaderPaths) {
            Resource resource = this.resourceLoader.getResource(templateLoaderPath + name);
            if (resource.exists()) {
                return resource;
            }
        }

        return null;
    }

    @Override
    public long getLastModified(Object templateSource) {
        Resource resource = (Resource) templateSource;
        try {
            return resource.lastModified();
        } catch (IOException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Could not obtain last-modified timestamp for FreeMarker template in {}: {}",
                    resource, e);
            }
            return -1;
        }
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        Resource resource = (Resource) templateSource;
        try {
            return new InputStreamReader(resource.getInputStream(), encoding);
        } catch (IOException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Could not find FreeMarker template: " + resource);
            }
            throw e;
        }
    }

    @Override
    public void closeTemplateSource(Object o) throws IOException {
        // nothing to close
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setTemplateLoaderPaths(Collection<String> templateLoaderPaths) {
        this.templateLoaderPaths = new LinkedHashSet<String>(templateLoaderPaths);
    }

    public void setTemplateLoaderPath(String templateLoaderPath) {
        this.templateLoaderPaths = Collections.singleton(templateLoaderPath);
    }

}
