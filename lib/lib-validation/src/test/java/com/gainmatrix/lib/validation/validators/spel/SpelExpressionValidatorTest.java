package com.gainmatrix.lib.validation.validators.spel;

import com.google.common.collect.Lists;
import org.hibernate.validator.engine.PathImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;

public class SpelExpressionValidatorTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidBeanA() throws Exception {
        TestBeanA testBean = new TestBeanA();
        testBean.setValue1("aaaaa");
        testBean.setValue2("aaaaa");

        Set<ConstraintViolation<TestBeanA>> constraintViolationsSet = validator.validate(testBean);
        Assert.assertEquals(0, constraintViolationsSet.size());
    }

    @Test
    public void testInvalidBeanA() throws Exception {
        TestBeanA testBean = new TestBeanA();
        testBean.setValue1("aaaaa");
        testBean.setValue2("bbbbb");

        Set<ConstraintViolation<TestBeanA>> constraintViolationsSet = validator.validate(testBean);
        Assert.assertEquals(1, constraintViolationsSet.size());

        List<ConstraintViolation<TestBeanA>> constraintViolationsList = Lists.newArrayList(constraintViolationsSet);

        ConstraintViolation<TestBeanA> violation = constraintViolationsList.get(0);
        Assert.assertNotNull(violation);
        Assert.assertEquals("{com.gainmatrix.validator.constraints.Spel.message}", violation.getMessage());
        Assert.assertEquals(PathImpl.createPathFromString(""), violation.getPropertyPath());
    }

    @Test
    public void testValidBeanB() throws Exception {
        TestBeanB testBean = new TestBeanB();
        testBean.setValue1("aaaaa");
        testBean.setValue2("aaaaa");

        Set<ConstraintViolation<TestBeanB>> constraintViolationsSet = validator.validate(testBean);
        Assert.assertEquals(0, constraintViolationsSet.size());
    }

    @Test
    public void testInvalidBeanBCase1() throws Exception {
        TestBeanB testBean = new TestBeanB();
        testBean.setValue1("bbbbb");
        testBean.setValue2("bbbbb");

        Set<ConstraintViolation<TestBeanB>> constraintViolationsSet = validator.validate(testBean);
        Assert.assertEquals(1, constraintViolationsSet.size());

        List<ConstraintViolation<TestBeanB>> constraintViolationsList = Lists.newArrayList(constraintViolationsSet);

        ConstraintViolation<TestBeanB> violation = constraintViolationsList.get(0);
        Assert.assertNotNull(violation);
        Assert.assertEquals("error.validation.wrong.value", violation.getMessage());
        Assert.assertEquals(PathImpl.createPathFromString("value1"), violation.getPropertyPath());
    }

    @Test
    public void testInvalidBeanBCase2() throws Exception {
        TestBeanB testBean = new TestBeanB();
        testBean.setValue1("aaaaa");
        testBean.setValue2("bbbbb");

        Set<ConstraintViolation<TestBeanB>> constraintViolationsSet = validator.validate(testBean);
        Assert.assertEquals(1, constraintViolationsSet.size());

        List<ConstraintViolation<TestBeanB>> constraintViolationsList = Lists.newArrayList(constraintViolationsSet);

        ConstraintViolation<TestBeanB> violation = constraintViolationsList.get(0);
        Assert.assertNotNull(violation);
        Assert.assertEquals("error.validation.mismatch", violation.getMessage());
        Assert.assertEquals(PathImpl.createPathFromString(""), violation.getPropertyPath());
    }

    @Test
    public void testFunctionValid() throws Exception {
        TestBeanC testBean = new TestBeanC();
        testBean.setValue1("value1");
        testBean.setValue2("value1");

        Set<ConstraintViolation<TestBeanC>> constraintViolationsSet = validator.validate(testBean);
        Assert.assertEquals(0, constraintViolationsSet.size());
    }

    @Test
    public void testFunctionInvalid() throws Exception {
        TestBeanC testBean = new TestBeanC();
        testBean.setValue1("value1");
        testBean.setValue2("value2");

        Set<ConstraintViolation<TestBeanC>> constraintViolationsSet = validator.validate(testBean);
        Assert.assertEquals(1, constraintViolationsSet.size());

        List<ConstraintViolation<TestBeanC>> constraintViolationsList = Lists.newArrayList(constraintViolationsSet);

        ConstraintViolation<TestBeanC> violation = constraintViolationsList.get(0);
        Assert.assertNotNull(violation);
        Assert.assertEquals("error.validation.function", violation.getMessage());
        Assert.assertEquals(PathImpl.createPathFromString(""), violation.getPropertyPath());
    }

    @SpelExpression("value1 == value2")
    private static class TestBeanA {

        private String value1;

        private String value2;

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }
    }

    @SpelExpression.List({
        @SpelExpression(value = "value1 == value2", message = "error.validation.mismatch"),
        @SpelExpression(value = "value1 == 'aaaaa'", message = "error.validation.wrong.value", subpath = "value1")
    })
    private static class TestBeanB {

        private String value1;

        private String value2;

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }
    }

    @SpelExpression(value = "isValid()", message = "error.validation.function")
    private static class TestBeanC {

        private String value1;

        private String value2;

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }

        public boolean isValid() {
            return (value1 != null) && (value2 != null) && (value1.equals(value2));
        }

    }
}
