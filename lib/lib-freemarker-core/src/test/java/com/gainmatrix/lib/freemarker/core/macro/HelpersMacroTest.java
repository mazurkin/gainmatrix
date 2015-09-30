package com.gainmatrix.lib.freemarker.core.macro;

import com.gainmatrix.lib.template.TextTemplate;
import com.gainmatrix.lib.template.TextTemplateFactory;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class HelpersMacroTest extends AbstractCoreMacroTest {

    @Resource
    private TextTemplateFactory freemarkerTextTemplateFactory;

    @Test
    public void testGetText() throws Exception {
        TextTemplate textTemplate = freemarkerTextTemplateFactory.getTemplate("macro/helpers/helpers-gettext", null);

        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("bean1", Boolean.TRUE)
            .build();

        String text = textTemplate.render(attributes);
        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("<div class='test1'>true</div>"));
    }

    @Test
    public void testIsEqualText() throws Exception {
        TextTemplate textTemplate = freemarkerTextTemplateFactory.getTemplate("macro/helpers/helpers-isequaltext", null);

        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("bean1", Boolean.TRUE)
            .put("bean2", "true")
            .build();

        String text = textTemplate.render(attributes);
        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("<div class='test1'>found</div>"));
    }

    @Test
    public void testGetTimeMs() throws Exception {
        TextTemplate textTemplate = freemarkerTextTemplateFactory.getTemplate("macro/helpers/helpers-gettimems", null);

        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("bean1", new Date(10083644544543L))
            .build();

        String text = textTemplate.render(attributes);
        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("<div class='test1'>10083644544543</div>"));
    }

    @Test
    public void testOutputMap() throws Exception {
        TextTemplate textTemplate = freemarkerTextTemplateFactory.getTemplate("macro/helpers/helpers-outputmap", null);

        Map<String, Object> map = ImmutableSortedMap.<String, Object>naturalOrder()
            .put("key1", "value1")
            .put("key2", "value2")
            .put("key3", Boolean.TRUE)
            .build();

        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("bean1", map)
            .build();

        String text = textTemplate.render(attributes);
        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("<div class='test1'>key1=\"value1\" key2=\"value2\" key3=\"true\"</div>"));
        Assert.assertTrue(text.contains("<div class='test2'>key1=\"value1\",key2=\"value2\",key3=\"true\"</div>"));
    }

    @Test
    public void testOutputList() throws Exception {
        TextTemplate textTemplate = freemarkerTextTemplateFactory.getTemplate("macro/helpers/helpers-outputlist", null);

        List<Object> list = Lists.<Object>newArrayList("value1", "value2", Boolean.TRUE);

        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("bean1", list)
            .build();

        String text = textTemplate.render(attributes);
        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("<div class='test1'>value1 value2 true</div>"));
        Assert.assertTrue(text.contains("<div class='test2'>value1,value2,true</div>"));
    }

}
