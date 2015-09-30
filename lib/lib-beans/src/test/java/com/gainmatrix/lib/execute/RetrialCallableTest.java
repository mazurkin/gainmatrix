package com.gainmatrix.lib.execute;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

public class RetrialCallableTest {

    private IMocksControl control;

    private RetrialCallable<Integer> retrialCallable;

    private TestCallable callable;

    @Before
    public void setUp() throws Exception {
        control = EasyMock.createStrictControl();

        callable = control.createMock(TestCallable.class);

        retrialCallable = new RetrialCallable<Integer>(callable, 3, IllegalStateException.class);
    }

    @Test
    public void testNormal() throws Exception {
        control.reset();

        EasyMock.expect(callable.call())
                .andReturn(1);

        control.replay();

        int result = retrialCallable.call();
        Assert.assertEquals(1, result);

        control.verify();
    }

    @Test
    public void testOneKnownException() throws Exception {
        control.reset();

        EasyMock.expect(callable.call())
                .andThrow(new IllegalStateException("test"));

        EasyMock.expect(callable.call())
                .andReturn(2);

        control.replay();

        int result = retrialCallable.call();
        Assert.assertEquals(2, result);

        control.verify();
    }

    @Test
    public void testOneUnknownException() throws Exception {
        control.reset();

        EasyMock.expect(callable.call())
                .andThrow(new IllegalArgumentException("test1"));

        control.replay();

        try {
            retrialCallable.call();
            Assert.fail("Exception is expected");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("test1", e.getMessage());
        }

        control.verify();
    }

    @Test
    public void testExceptions() throws Exception {
        control.reset();

        EasyMock.expect(callable.call())
                .andThrow(new IllegalStateException("test1"));

        EasyMock.expect(callable.call())
                .andThrow(new IllegalStateException("test2"));

        EasyMock.expect(callable.call())
                .andThrow(new IllegalStateException("test3"));

        control.replay();

        try {
            retrialCallable.call();
            Assert.fail("Exception is expected");
        } catch (IllegalStateException e) {
            Assert.assertEquals("test3", e.getMessage());
        }

        control.verify();
    }

    private static interface TestCallable extends Callable<Integer> {

    }

}
