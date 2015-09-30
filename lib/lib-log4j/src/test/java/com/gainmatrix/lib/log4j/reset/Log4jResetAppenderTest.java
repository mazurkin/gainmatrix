package com.gainmatrix.lib.log4j.reset;

import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;

public class Log4jResetAppenderTest {

    @Test
    public void testResetFileAppenders() throws Exception {
        Logger logger1 = Logger.getLogger("com.gainmatrix.lib.log4j.45a6fef0d959");
        Logger logger2 = Logger.getLogger("com.gainmatrix.lib.log4j.f54a4ea0d149");
        Logger logger = Logger.getLogger("com.gainmatrix.lib.log4j.root");

        File appender1File = new File(System.getProperty("java.io.tmpdir"), "gainmatrix-lib-log1.log");
        File appender2File = new File(System.getProperty("java.io.tmpdir"), "gainmatrix-lib-log2.log");
        File appender3File = new File(System.getProperty("java.io.tmpdir"), "gainmatrix-lib-log3.log");

        //

        logger1.debug("Test message 1");
        logger2.debug("Test message 2");
        logger.info("Test message 3");

        Assert.assertTrue(appender1File.isFile());
        Assert.assertTrue(appender1File.length() > 0);
        Assert.assertTrue(appender2File.isFile());
        Assert.assertTrue(appender2File.length() > 0);
        Assert.assertTrue(appender3File.isFile());
        Assert.assertTrue(appender3File.length() > 0);

        //

        Logger resetLogger = Logger.getLogger("com.gainmatrix.lib.log4j.a18da52ff52d");
        resetLogger.info("resetAll");

        //

        logger1.debug("Test message 4");
        logger2.debug("Test message 5");
        logger.info("Test message 6");

        //

        if (!appender1File.delete()) {
            System.out.println("Fail to delete file: " + appender1File);
        }

        if (!appender2File.delete()) {
            System.out.println("Fail to delete file: " + appender2File);
        }

        if (!appender3File.delete()) {
            System.out.println("Fail to delete file: " + appender3File);
        }
    }

}
