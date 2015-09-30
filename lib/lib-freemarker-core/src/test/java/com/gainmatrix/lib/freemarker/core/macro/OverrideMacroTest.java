package com.gainmatrix.lib.freemarker.core.macro;

import com.gainmatrix.lib.template.TextTemplate;
import com.gainmatrix.lib.template.TextTemplateFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.Map;

public class OverrideMacroTest extends AbstractCoreMacroTest {

    @Resource
    private TextTemplateFactory freemarkerTextTemplateFactory;

    @Test
    public void test() throws Exception {
        Map<String, Object> attributes = Collections.emptyMap();

        //

        TextTemplate textTemplate1 = freemarkerTextTemplateFactory.getTemplate("macro/override/test-override-template1", null);

        String text1 = textTemplate1.render(attributes);
        Assert.assertNotNull(text1);
        Assert.assertTrue(text1.contains("{TEST1}<html><body><div class=\"local\">Hello!</div></body></html>{TEST1}"));

        //

        TextTemplate textTemplate2 = freemarkerTextTemplateFactory.getTemplate("macro/override/test-override-template2", null);

        String text2 = textTemplate2.render(attributes);
        Assert.assertNotNull(text2);
        Assert.assertTrue(text2.contains("{TEST1}<html><body><div class=\"global\">Hello!</div></body></html>{TEST1}"));
    }

}
