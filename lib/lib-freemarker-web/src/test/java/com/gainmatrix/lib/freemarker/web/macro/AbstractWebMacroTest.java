package com.gainmatrix.lib.freemarker.web.macro;

import com.gainmatrix.lib.freemarker.web.attributes.FreemarkerAttributePublisher;
import com.gainmatrix.lib.time.ChronometerUtils;
import com.gainmatrix.lib.web.attribute.render.RenderContext;
import com.gainmatrix.lib.web.context.DelegatingWebApplicationContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.Locale;
import java.util.TimeZone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test/freemarker/context/test.xml")
public abstract class AbstractWebMacroTest {

    @Resource
    private ApplicationContext applicationContext;

    private RenderContext renderContext;

    private RequestContext requestContext;

    @Before
    public void setUp() throws Exception {
        MockServletContext servletContext = new MockServletContext();
        servletContext.setContextPath("test/");

        MockHttpServletRequest request = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse response = new MockHttpServletResponse();

        FreemarkerAttributePublisher freemarkerAttributePublisher = new FreemarkerAttributePublisher();
        freemarkerAttributePublisher.publish(request);

        WebApplicationContext webApplicationContext = new DelegatingWebApplicationContext(applicationContext, servletContext);
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webApplicationContext);

        requestContext = new RequestContext(request, response, servletContext,
            Collections.<String, Object>emptyMap());
        requestContext.setDefaultHtmlEscape(true);

        renderContext = new RenderContext();
        renderContext.setApplicationVersion("1.0-test");
        renderContext.setLocale(new Locale("en", "US"));
        renderContext.setTimezone(TimeZone.getTimeZone("UTC"));
        renderContext.setNow(ChronometerUtils.parseMoment("2010-02-12 10:20:00.000 UTC"));
    }

    public RenderContext getRenderContext() {
        return renderContext;
    }

    public RequestContext getRequestContext() {
        return requestContext;
    }
}
