package com.gainmatrix.lib.validation.validators.spel;

import org.apache.commons.lang.StringUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SpelExpressionValidator implements ConstraintValidator<SpelExpression, Object> {

    private static final ExpressionParser PARSER = createExpressionParser();

    private Expression expression;

    private SpelExpression annotation;

    private static ExpressionParser createExpressionParser() {
        SpelParserConfiguration configuration = new SpelParserConfiguration(true, true);
        return new SpelExpressionParser(configuration);
    }

    @Override
    public void initialize(SpelExpression constraintAnnotation) {
        this.annotation = constraintAnnotation;

        String expressionText = constraintAnnotation.value();
        if (expressionText == null) {
            throw new IllegalArgumentException("The expression specified in @" +
                    SpelExpression.class.getSimpleName() + " must not be null.");
        }

        expression = PARSER.parseExpression(expressionText);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean valid = expression.getValue(value, Boolean.class);

        if ((!valid) && (StringUtils.isNotEmpty(annotation.subpath()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(annotation.message())
                    .addNode(annotation.subpath())
                    .addConstraintViolation();
        }

        return valid;
    }

}
