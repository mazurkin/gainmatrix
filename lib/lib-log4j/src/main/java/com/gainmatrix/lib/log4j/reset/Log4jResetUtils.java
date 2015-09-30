package com.gainmatrix.lib.log4j.reset;

// CHECKSTYLE-OFF: IllegalImportCheck

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

// CHECKSTYLE-ON: IllegalImportCheck

/**
 * Utitity class with Log4j file appender functions
 */
public final class Log4jResetUtils {

    private Log4jResetUtils() {
    }

    /**
     * Detach all file appenders from their files. When function is called all file appenders close their files
     * and then reopen theese files again.
     */
    public static void resetAllFileAppenders() {
        // Get all loggers
        Map<String, Logger> loggersMap = new HashMap<String, Logger>();

        @SuppressWarnings("unchecked")
        Enumeration<Logger> currentloggers = LogManager.getCurrentLoggers();

        while (currentloggers.hasMoreElements()) {
            Logger logger = currentloggers.nextElement();
            appendLoggerWithParents(logger, loggersMap);
        }

        // Get all appenders
        Map<String, FileAppender> appendersMap = new HashMap<String, FileAppender>();

        for (Logger logger : loggersMap.values()) {
            @SuppressWarnings("unchecked")
            Enumeration<Appender> appenders = logger.getAllAppenders();

            while (appenders.hasMoreElements()) {
                Appender appender = appenders.nextElement();
                if (appender instanceof FileAppender) {
                    appendersMap.put(appender.getName(), (FileAppender) appender);
                }
            }
        }

        // Reset all file appenders
        for (FileAppender fileAppender : appendersMap.values()) {
            String file = fileAppender.getFile();
            boolean append = fileAppender.getAppend();
            boolean bufferedIO = fileAppender.getBufferedIO();
            int bufferSize = fileAppender.getBufferSize();

            try {
                fileAppender.setFile(file, append, bufferedIO, bufferSize);
            } catch (IOException e) {
                LogLog.error("Fail to reset log file: " + file);
            }
        }
    }

    private static void appendLoggerWithParents(Logger logger, Map<String, Logger> loggersMap) {
        loggersMap.put(logger.getName(), logger);

        Category parent = logger.getParent();
        if (parent instanceof Logger) {
            appendLoggerWithParents((Logger) parent, loggersMap);
        }
    }

}
