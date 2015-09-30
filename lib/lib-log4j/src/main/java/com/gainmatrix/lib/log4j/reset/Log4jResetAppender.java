package com.gainmatrix.lib.log4j.reset;

// CHECKSTYLE-OFF: IllegalImportCheck
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
// CHECKSTYLE-ON: IllegalImportCheck

/**
 * Dummy appender looks for a special log event with INFO level. If such an event is found
 * this appender resets all file appenders. Incoming log events aren't written anywhere by the appender.
 */
public class Log4jResetAppender extends AppenderSkeleton {

    private static final String DEFAULT_CODE = "reset";

    private String code = DEFAULT_CODE;

    @Override
    protected void append(LoggingEvent event) {
        if (event.getLevel() == Level.INFO) {
            String message = event.getRenderedMessage();
            if (code.equals(message)) {
                Log4jResetUtils.resetAllFileAppenders();
            }
        }
    }

    @Override
    public void close() {
        // do nothing
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
