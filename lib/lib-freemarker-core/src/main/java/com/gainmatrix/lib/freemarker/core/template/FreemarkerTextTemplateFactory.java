package com.gainmatrix.lib.freemarker.core.template;

import com.gainmatrix.lib.template.TextTemplate;
import com.gainmatrix.lib.template.TextTemplateException;
import com.gainmatrix.lib.template.TextTemplateFactory;
import com.google.common.base.Preconditions;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Locale;

public class FreemarkerTextTemplateFactory implements TextTemplateFactory {

    private static final String DEFAULT_PREFIX = null;

    private static final String DEFAULT_SUFFIX = ".ftl";

    private String prefix = DEFAULT_PREFIX;

    private String suffix = DEFAULT_SUFFIX;

    private Configuration configuration;

    @Override
    public TextTemplate getTemplate(String name, Locale locale) throws TextTemplateException {
        Preconditions.checkNotNull(name, "Name is null");

        // Check is the locale set
        Locale usedLocale = (locale != null) ? locale : Locale.getDefault();

        // Build full path to template
        StringBuilder templatePathBuilder = new StringBuilder();

        if (StringUtils.hasText(prefix)) {
            templatePathBuilder.append(prefix);
        }

        templatePathBuilder.append(name);

        if (StringUtils.hasText(suffix)) {
            templatePathBuilder.append(suffix);
        }

        // Load template
        Template template;

        try {
            template = configuration.getTemplate(templatePathBuilder.toString(), usedLocale);
        } catch (IOException e) {
            throw new TextTemplateException("Fail to load template: " + name, e);
        }

        return new FreemarkerTextTemplate(name, template);
    }

    @Required
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
