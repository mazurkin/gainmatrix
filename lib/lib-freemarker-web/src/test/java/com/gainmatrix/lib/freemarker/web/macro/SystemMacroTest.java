package com.gainmatrix.lib.freemarker.web.macro;

import com.gainmatrix.lib.template.TextTemplate;
import com.gainmatrix.lib.template.TextTemplateFactory;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

public class SystemMacroTest extends AbstractWebMacroTest {

    @Resource
    private TextTemplateFactory freemarkerTextTemplateFactory;

    @Test
    public void test() throws Exception {
        List<Object> beanList = Lists.<Object>newArrayList("value1", "value2", "value3");

        Map<String, Object> beanMap = ImmutableMap.<String, Object>builder()
            .put("key1", "value1")
            .put("key2", "value2")
            .build();

        @SuppressWarnings("ThrowableInstanceNeverThrown")
        Throwable exception = new RuntimeException("Test exception");

        //

        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("requestContext", getRequestContext())
            .put("renderContext", getRenderContext())
            .put("beanList", beanList)
            .put("beanMap", beanMap)
            .put("exception", exception)
            .build();

        TextTemplate textTemplate = freemarkerTextTemplateFactory.getTemplate("macro/system/test-macros", null);

        String text = textTemplate.render(attributes);
        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("<div class=\"test1\">[test.message]</div>"));
        Assert.assertTrue(text.contains("<div class=\"test2\">[test.message]</div>"));
        Assert.assertTrue(text.contains("<div class=\"test3\">/some/page.html</div>"));
        Assert.assertTrue(text.contains("<div class=\"test4\">/some/page.html?id=value1</div>"));
        Assert.assertTrue(text.contains("<div class=\"test5\">/some/page.html?r=1.0-test</div>"));
    }

}
