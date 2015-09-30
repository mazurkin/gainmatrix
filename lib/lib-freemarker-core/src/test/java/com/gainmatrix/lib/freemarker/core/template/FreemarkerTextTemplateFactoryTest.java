package com.gainmatrix.lib.freemarker.core.template;

import com.gainmatrix.lib.template.TextTemplate;
import com.gainmatrix.lib.template.TextTemplateFactory;
import com.google.common.collect.ImmutableMap;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test/freemarker/context/test.xml")
public class FreemarkerTextTemplateFactoryTest {

    @Resource
    private TextTemplateFactory freemarkerTextTemplateFactory;

    @Test
    public void testTextTemplate() throws Exception {
        TextTemplate textTemplate = freemarkerTextTemplateFactory.getTemplate("text-template", null);

        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("bean1", "text1")
            .put("bean2", "text2")
            .build();

        String text = textTemplate.render(attributes);
        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("text1"));
        Assert.assertTrue(text.contains("text2"));
    }

}
