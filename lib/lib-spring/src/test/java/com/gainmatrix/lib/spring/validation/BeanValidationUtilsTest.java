package com.gainmatrix.lib.spring.validation;

import junit.framework.Assert;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BeanValidationUtilsTest {

    private IMocksControl control;

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        control = EasyMock.createStrictControl();

        validator = control.createMock(Validator.class);
    }

    @Test
    public void testValidBean() throws Exception {
        TestBean testBean = new TestBean();

        control.reset();

        EasyMock.expect(validator.supports(EasyMock.<Class<?>>anyObject()))
            .andReturn(true);

        validator.validate(EasyMock.same(testBean), EasyMock.<Errors>anyObject());

        control.replay();

        BeanValidationUtils.checkValidity(testBean, validator);

        control.verify();
    }

    @Test
    public void testInvalidBean() throws Exception {
        TestBean testBean = new TestBean();

        Capture<Errors> errorsCapture = new Capture<Errors>() {
            @Override
            public void setValue(Errors errors) {
                super.setValue(errors);
                errors.reject("test rejection");
            }
        };

        control.reset();

        EasyMock.expect(validator.supports(EasyMock.<Class<?>>anyObject()))
            .andReturn(true);

        validator.validate(
            EasyMock.same(testBean),
            EasyMock.capture(errorsCapture)
        );

        control.replay();

        try {
            BeanValidationUtils.checkValidity(testBean, validator);
            Assert.fail("Exception is expected to occur");
        } catch (IllegalArgumentException e) {
            // do nothing
        }

        control.verify();
    }

    private static class TestBean {

    }

}
