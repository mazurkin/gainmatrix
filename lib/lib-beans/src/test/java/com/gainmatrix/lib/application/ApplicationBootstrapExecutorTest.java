package com.gainmatrix.lib.application;

import junit.framework.Assert;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

public class ApplicationBootstrapExecutorTest {

    private IMocksControl control;

    private ApplicationBootstrap applicationBootstrap;

    @Before
    public void setUp() throws Exception {
        control = EasyMock.createStrictControl();

        applicationBootstrap = control.createMock(ApplicationBootstrap.class);
    }

    @Test
    public void testNormalExecute() throws Exception {
        String[] arguments = { "1", "2", "3" };

        control.reset();

        applicationBootstrap.init(
            EasyMock.aryEq(arguments)
        );

        EasyMock.expect(applicationBootstrap.run())
            .andReturn(6);

        applicationBootstrap.destroy();

        control.replay();

        int exitCode = ApplicationBootstrapExecutor.execute(arguments, applicationBootstrap);
        Assert.assertEquals(6, exitCode);

        control.verify();
    }

    @Test
    public void testFailedInit() throws Exception {
        String[] arguments = { "1", "2", "3" };

        control.reset();

        applicationBootstrap.init(
            EasyMock.aryEq(arguments)
        );

        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());

        control.replay();

        int exitCode = ApplicationBootstrapExecutor.execute(arguments, applicationBootstrap);
        Assert.assertEquals(-1, exitCode);

        control.verify();
    }

    @Test
    public void testFailedRun() throws Exception {
        String[] arguments = { "1", "2", "3" };

        control.reset();

        applicationBootstrap.init(
            EasyMock.aryEq(arguments)
        );

        EasyMock.expect(applicationBootstrap.run())
            .andThrow(new IllegalArgumentException());

        applicationBootstrap.destroy();

        control.replay();

        int exitCode = ApplicationBootstrapExecutor.execute(arguments, applicationBootstrap);
        Assert.assertEquals(-1, exitCode);

        control.verify();
    }

    @Test
    public void testNullInstance() throws Exception {
        String[] arguments = { "1", "2", "3" };

        int exitCode = ApplicationBootstrapExecutor.execute(arguments, null);
        Assert.assertEquals(-1, exitCode);
    }
}
