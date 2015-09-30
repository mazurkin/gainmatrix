package com.gainmatrix.lib.web.servlet;

import com.gainmatrix.lib.spring.i18n.timezone.context.TimezoneContext;
import com.gainmatrix.lib.spring.i18n.timezone.context.TimezoneContextHolder;
import com.gainmatrix.lib.web.attribute.AttributePublisher;
import com.gainmatrix.lib.web.resolver.timezone.TimezoneResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

/**
 * Расширенный класс сервлета с поддержкой контекста таймзоны и автоматической публикацией
 * аттрибутов рендеринга
 */
public class ExtendedDispatcherServlet extends DispatcherServlet {

    public static final String TIMEZONE_RESOLVER_BEAN_NAME = "timezoneResolver";

    public static final String TIMEZONE_RESOLVER_ATTRIBUTE =
            ExtendedDispatcherServlet.class.getName() + ".TIMEZONE_RESOLVER";

    public static final String ATTRIBUTES_PUBLISHER_BEAN_NAME = "attributePublisher";

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedDispatcherServlet.class);

    private TimezoneResolver timezoneResolver;

    private List<AttributePublisher> attributePublishers;

    private boolean threadContextInheritable = false;

    @Override
    protected void initStrategies(ApplicationContext context) {
        super.initStrategies(context);

        initTimeZoneResolver(context);
        initAttributesPublisher(context);
    }

    protected void initTimeZoneResolver(ApplicationContext context) {
        try {
            this.timezoneResolver = context.getBean(TIMEZONE_RESOLVER_BEAN_NAME, TimezoneResolver.class);
        } catch (NoSuchBeanDefinitionException ex) {
            this.timezoneResolver = getDefaultStrategy(context, TimezoneResolver.class);
        }

        LOGGER.debug("Using TimezoneResolver [{}]", this.timezoneResolver);
    }

    protected void initAttributesPublisher(ApplicationContext context) {
        try {
            AttributePublisher attributePublisher =
                    context.getBean(ATTRIBUTES_PUBLISHER_BEAN_NAME, AttributePublisher.class);
            this.attributePublishers = Arrays.asList(attributePublisher);
        } catch (NoSuchBeanDefinitionException ex) {
            this.attributePublishers = getDefaultStrategies(context, AttributePublisher.class);
        }

        LOGGER.debug("Using AttributesPublisher [{}]", this.attributePublishers);
    }

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(TIMEZONE_RESOLVER_ATTRIBUTE, timezoneResolver);

        // Публикация временной зоны
        TimezoneContext previousTimezoneContext = TimezoneContextHolder.getTimezoneContext();
        TimezoneContext timezoneContext = buildTimezoneContext(request);
        TimezoneContextHolder.setTimezoneContext(timezoneContext, this.threadContextInheritable);

        try {
            super.doService(request, response);
        } finally {
            TimezoneContextHolder.setTimezoneContext(previousTimezoneContext, this.threadContextInheritable);
        }
    }

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Публикация аттрибутов
        if (attributePublishers != null) {
            for (AttributePublisher attributePublisher : attributePublishers) {
                attributePublisher.publish(request);
            }
        }

        super.doDispatch(request, response);
    }

    protected TimezoneContext buildTimezoneContext(final HttpServletRequest request) {
        return new TimezoneContext() {
            public TimeZone getTimezone() {
                return timezoneResolver.resolveTimezone(request);
            }
            public String toString() {
                return getTimezone().toString();
            }
        };
    }

    public void setThreadContextInheritable(boolean threadContextInheritable) {
        // Устанавливаем флаг у себя
        this.threadContextInheritable = threadContextInheritable;
        // Устанавливаем аттрибут у родителя
        super.setThreadContextInheritable(threadContextInheritable);
    }

    @Override
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Устанавливаем таймзону - она будет автоматически использована тэгами JSTL
        Config.set(request, Config.FMT_TIME_ZONE, TimezoneContextHolder.getTimezone());
        Config.set(request, Config.FMT_LOCALE, LocaleContextHolder.getLocale());
        // Действие по умолчанию
        super.render(mv, request, response);
    }

}
