package com.gainmatrix.lib.reflection;

import com.gainmatrix.lib.beans.Identified;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class BeanReflectionUtilsTest {

    @Test
    public void test() throws Exception {
        BeanC bean1 = new BeanC();
        bean1.setId(1);
        bean1.setValue1("aaa");
        bean1.setValue2(null);
        bean1.setValue5(22L);
        bean1.getBeanB().setId(2);
        bean1.getBeanB().setValue3("bbb");
        bean1.getBeanB().setValue4(new Date(100));

        BeanC bean2 = new BeanC();
        bean2.setId(1);
        bean2.setValue1("aaa");
        bean2.setValue2(null);
        bean2.setValue5(22L);
        bean2.getBeanB().setId(3);
        bean2.getBeanB().setValue3("ccc");
        bean2.getBeanB().setValue4(new Date(200));

        BeanC bean3 = new BeanC();
        bean3.setId(1);
        bean3.setValue1("aaa");
        bean3.setValue2(null);
        bean3.setValue5(22L);
        bean3.getBeanB().setId(2);
        bean3.getBeanB().setValue3("bbb");
        bean3.getBeanB().setValue4(new Date(100));

        BeanReflectionUtils.checkEqual(bean1, bean2);
        BeanReflectionUtils.checkEqual(bean2, bean1);

        BeanReflectionUtils.checkEqual(bean1, bean2, 0);
        BeanReflectionUtils.checkEqual(bean2, bean1, 0);

        try {
            BeanReflectionUtils.checkEqual(bean1, bean2, 1);
            Assert.fail("Exception is expected");
        } catch (BeanReflectionMismatchException e) {
            //
        }

        BeanReflectionUtils.checkEqual(bean1, bean3, 1);
        BeanReflectionUtils.checkEqual(bean3, bean1, 1);

        BeanReflectionUtils.checkEqual(bean1, bean3, 2);
        BeanReflectionUtils.checkEqual(bean3, bean1, 2);
    }

    private static class BeanA implements Identified<Long> {

        private long id;

        private String value1;

        private Integer value2;

        @Override
        public Long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public Integer getValue2() {
            return value2;
        }

        public void setValue2(Integer value2) {
            this.value2 = value2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BeanA beanA = (BeanA) o;

            if (id != beanA.id) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }
    }

    private static class BeanB implements Identified<Long> {

        private long id;

        private String value3;

        private Date value4;

        @Override
        public Long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getValue3() {
            return value3;
        }

        public void setValue3(String value3) {
            this.value3 = value3;
        }

        public Date getValue4() {
            return value4;
        }

        public void setValue4(Date value4) {
            this.value4 = value4;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BeanB beanB = (BeanB) o;

            if (id != beanB.id) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }
    }

    private static class BeanC extends BeanA {

        private long value5;

        private BeanB beanB = new BeanB();

        public long getValue5() {
            return value5;
        }

        public void setValue5(long value5) {
            this.value5 = value5;
        }

        public BeanB getBeanB() {
            return beanB;
        }

        public void setBeanB(BeanB beanB) {
            this.beanB = beanB;
        }
    }
}
