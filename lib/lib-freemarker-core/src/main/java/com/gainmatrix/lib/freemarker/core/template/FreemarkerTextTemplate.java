package com.gainmatrix.lib.freemarker.core.template;

import com.gainmatrix.lib.template.TextTemplate;
import com.gainmatrix.lib.template.TextTemplateException;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class FreemarkerTextTemplate implements TextTemplate {

    private final String name;

    private final Template template;

    public FreemarkerTextTemplate(String name, Template template) {
        this.name = name;
        this.template = template;
    }

    @Override
    public String render(Map<String, Object> attributes) throws TextTemplateException {
        StringWriter writer = new StringWriter();

        try {
            template.process(attributes, writer);
        } catch (IOException e) {
            throw new TextTemplateException("Fail to process template: " + name, e);
        } catch (TemplateException e) {
            throw new TextTemplateException("Fail to process template: " + name, e);
        }

        return writer.toString();
    }

}
