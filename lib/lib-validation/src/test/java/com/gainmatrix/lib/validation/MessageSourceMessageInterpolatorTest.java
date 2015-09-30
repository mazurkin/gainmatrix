package com.gainmatrix.lib.validation;

import com.google.common.collect.ImmutableMap;
import junit.framework.Assert;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;

import javax.validation.MessageInterpolator;
import javax.validation.metadata.ConstraintDescriptor;

import java.util.Locale;
import java.util.Map;

public class MessageSourceMessageInterpolatorTest {
    
    private IMocksControl control;

    private MessageSource messageSource;

    private MessageInterpolator.Context context;

    private ConstraintDescriptor<?> constraintDescriptor;

    private MessageSourceMessageInterpolator messageSourceMessageInterpolator;

    @Before
    public void setUp() throws Exception {
        control = EasyMock.createStrictControl();

        messageSource = control.createMock(MessageSource.class);
        context = control.createMock(MessageInterpolator.Context.class);
        constraintDescriptor = control.createMock(ConstraintDescriptor.class);

        messageSourceMessageInterpolator = new MessageSourceMessageInterpolator();
        messageSourceMessageInterpolator.setMessageSource(messageSource);
    }

    @Test
    public void testSimpleMessage() throws Exception {
        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("key1", "value1")
            .build();

        control.reset();

        EasyMock.expect(messageSource.getMessage(
            EasyMock.<String>eq("error.validation.some.standard.error.code"),
            EasyMock.<Object[]>isNull(),
            EasyMock.<Locale>eq(Locale.ENGLISH)
        )).andReturn("some string");

        EasyMock.<ConstraintDescriptor<?>>expect(context.getConstraintDescriptor())
            .andReturn(constraintDescriptor);

        EasyMock.expect(constraintDescriptor.getAttributes())
            .andReturn(attributes);

        control.replay();

        String result = messageSourceMessageInterpolator.interpolate("{some.standard.error.code}",
            context, Locale.ENGLISH);
        Assert.assertEquals("some string", result);

        control.verify();
    }

    @Test
    public void testInterpolatedMessage() throws Exception {
        Map<String, Object> attributes = ImmutableMap.<String, Object>builder()
            .put("key1", "value1")
            .build();

        control.reset();

        EasyMock.expect(messageSource.getMessage(
            EasyMock.<String>eq("error.validation.some.standard.error.code"),
            EasyMock.<Object[]>isNull(),
            EasyMock.<Locale>eq(Locale.ENGLISH)
        )).andReturn("some string with {key1} templating");

        EasyMock.<ConstraintDescriptor<?>>expect(context.getConstraintDescriptor())
            .andReturn(constraintDescriptor);

        EasyMock.expect(constraintDescriptor.getAttributes())
            .andReturn(attributes);

        control.replay();

        String result = messageSourceMessageInterpolator.interpolate("{some.standard.error.code}",
            context, Locale.ENGLISH);
        Assert.assertEquals("some string with value1 templating", result);

        control.verify();
    }

}
