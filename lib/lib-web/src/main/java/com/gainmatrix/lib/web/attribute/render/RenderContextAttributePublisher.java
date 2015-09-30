package com.gainmatrix.lib.web.attribute.render;

import com.gainmatrix.lib.spring.i18n.ClientI18nResolver;
import com.gainmatrix.lib.time.Chronometer;
import com.gainmatrix.lib.web.attribute.AttributePublisher;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;

/**
 * Basic render attribute publisher
 */
public class RenderContextAttributePublisher implements AttributePublisher {

    public static final String DEFAULT_ATTRIBUTE_NAME = "renderContext";

    private Chronometer chronometer;

    private ClientI18nResolver clientI18nResolver;

    private String applicationVersion;

    private String attributeName = DEFAULT_ATTRIBUTE_NAME;

    @Override
    public void publish(HttpServletRequest request) {
        RenderContext renderContext = createRenderContext();

        publishRenderContext(renderContext);

        request.setAttribute(attributeName, renderContext);
    }

    protected RenderContext resolve(HttpServletRequest request) {
        return (RenderContext) request.getAttribute(attributeName);
    }

    protected RenderContext createRenderContext() {
        return new RenderContext();
    }

    protected void publishRenderContext(RenderContext renderContext) {
        renderContext.setNow(chronometer.getCurrentMoment());
        renderContext.setApplicationVersion(applicationVersion);
        renderContext.setLocale(clientI18nResolver.getLocale());
        renderContext.setTimezone(clientI18nResolver.getTimeZone());
    }

    @Required
    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    @Required
    public void setClientI18nResolver(ClientI18nResolver clientI18nResolver) {
        this.clientI18nResolver = clientI18nResolver;
    }

    @Required
    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

}
