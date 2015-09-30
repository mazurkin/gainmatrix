package com.gainmatrix.lib.freemarker.web.view;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Freemarker view resolver allows to specify configuration instance explicitly
 */
public class ConfigurableFreeMarkerViewResolver extends FreeMarkerViewResolver {

    private Configuration configuration;

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        FreeMarkerView view = (FreeMarkerView) super.buildView(viewName);
        view.setConfiguration(configuration);
        return view;
    }

    @Required
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

}
