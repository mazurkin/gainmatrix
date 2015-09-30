package com.gainmatrix.lib.freemarker.web.macro;

import com.gainmatrix.lib.template.TextTemplate;
import com.gainmatrix.lib.template.TextTemplateFactory;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Map;

public class FormMacroTest extends AbstractWebMacroTest {

    @Resource
    private TextTemplateFactory freemarkerTextTemplateFactory;

    @Test
    public void test() throws Exception {
        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("requestContext", getRequestContext())
            .put("renderContext", getRenderContext())
            .build();

        TextTemplate textTemplate = freemarkerTextTemplateFactory.getTemplate("macro/form/test-macros", null);

        String text = textTemplate.render(attributes);
        Assert.assertNotNull(text);
    }

}
