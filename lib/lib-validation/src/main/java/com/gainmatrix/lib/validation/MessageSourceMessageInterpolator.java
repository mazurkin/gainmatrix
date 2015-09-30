package com.gainmatrix.lib.validation;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.MessageInterpolator;

import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Localizing validator messages with Spring's MessageSource source
 * @see org.springframework.context.MessageSource
 */
public class MessageSourceMessageInterpolator implements MessageInterpolator {

    private static final String DEFAULT_MESSAGE_PREFIX = "error.validation.";

    private static final Pattern PATTERN_PLACEHOLDER = Pattern.compile("\\{(.+?)\\}");

    private MessageSource messageSource;

    private String messagePrefix = DEFAULT_MESSAGE_PREFIX;

    @Override
    public String interpolate(String messageCode, Context context) {
        return interpolate(messageCode, context, LocaleContextHolder.getLocale());
    }

    @Override
    public String interpolate(String messageCode, Context context, Locale locale) {
        Preconditions.checkNotNull(messageCode, "Message template is null");

        String messageString = messageCode;

        // Remove curved braces from standard messages
        if ((messageString.length() >= 2) && messageString.startsWith("{") && messageString.endsWith("}")) {
            messageString = messageString.substring(1, messageString.length() - 1);
        }

        // Add a predifined suffix
        if ((messagePrefix != null) && (!messageString.startsWith(messagePrefix))) {
            messageString = messagePrefix + messageString;
        }

        // Load a message template
        String messageTemplate = messageSource.getMessage(messageString, null, locale);

        // Replace all named placeholders to the validator values
        StringBuffer messageBuilder = new StringBuffer();

        Map<String, Object> attributes = context.getConstraintDescriptor().getAttributes();

        Matcher placeholderMatcher = PATTERN_PLACEHOLDER.matcher(messageTemplate);

        while (placeholderMatcher.find()) {
            String placeholderName = placeholderMatcher.group(1);
            Object placeholderObject = attributes.get(placeholderName);
            String placeholderValue = String.valueOf(placeholderObject);
            placeholderMatcher.appendReplacement(messageBuilder, placeholderValue);
        }

        placeholderMatcher.appendTail(messageBuilder);

        return messageBuilder.toString();
    }

    @Required
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setMessagePrefix(String messagePrefix) {
        this.messagePrefix = messagePrefix;
    }

}
