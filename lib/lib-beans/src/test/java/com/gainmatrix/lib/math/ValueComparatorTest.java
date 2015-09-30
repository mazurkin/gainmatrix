package com.gainmatrix.lib.math;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ValueComparatorTest {

    @Test
    public void test() {
        ValueComparator<BigDecimal> comparator = ValueComparator.compare(new BigDecimal("200.00"));

        Assert.assertTrue(comparator.isEqualTo(new BigDecimal("200.00")));
        Assert.assertFalse(comparator.isEqualTo(new BigDecimal("100.00")));

        Assert.assertFalse(comparator.isNotEqualTo(new BigDecimal("200.00")));
        Assert.assertTrue(comparator.isNotEqualTo(new BigDecimal("100.00")));

        Assert.assertTrue(comparator.isLessThan(new BigDecimal("300.00")));
        Assert.assertFalse(comparator.isLessThan(new BigDecimal("100.00")));
        Assert.assertFalse(comparator.isLessThan(new BigDecimal("200.00")));

        Assert.assertFalse(comparator.isNotLessThan(new BigDecimal("300.00")));
        Assert.assertTrue(comparator.isNotLessThan(new BigDecimal("100.00")));
        Assert.assertTrue(comparator.isNotLessThan(new BigDecimal("200.00")));

        Assert.assertTrue(comparator.isMoreThan(new BigDecimal("100.00")));
        Assert.assertFalse(comparator.isMoreThan(new BigDecimal("300.00")));
        Assert.assertFalse(comparator.isMoreThan(new BigDecimal("200.00")));

        Assert.assertFalse(comparator.isNotMoreThan(new BigDecimal("100.00")));
        Assert.assertTrue(comparator.isNotMoreThan(new BigDecimal("300.00")));
        Assert.assertTrue(comparator.isNotMoreThan(new BigDecimal("200.00")));
    }

    @Test
    public void testSample() throws Exception {
        BigDecimal payment = new BigDecimal("343.23");
        BigDecimal balance = new BigDecimal("643.32");

        Assert.assertTrue(ValueComparator.compare(payment).isNotMoreThan(balance));
    }
}
