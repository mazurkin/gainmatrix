package com.gainmatrix.lib.validation.validators.js;

import com.gainmatrix.lib.business.exception.SystemIntegrityException;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JsExpressionValidator implements ConstraintValidator<JsExpression, Object> {

    private static final String ROOT_BEAN_NAME = "bean";

    private static final String ENGINE_TYPE = "JavaScript";

    private static final ScriptEngine ENGINE = createScriptEngine();

    private String expressionText;

    private JsExpression annotation;

    private static ScriptEngine createScriptEngine() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

        ScriptEngine engine = scriptEngineManager.getEngineByName(ENGINE_TYPE);

        // Check: is script engine theadsafe
        ScriptEngineFactory scriptEngineFactory = engine.getFactory();
        Object threadSafeMarker = scriptEngineFactory.getParameter("THREADING");
        Preconditions.checkNotNull(threadSafeMarker, "Script engine is not thread-safe");

        return engine;
    }

    @Override
    public void initialize(JsExpression constraintAnnotation) {
        this.annotation = constraintAnnotation;

        expressionText = constraintAnnotation.value();
        if (expressionText == null) {
            throw new IllegalArgumentException("The expression specified in @" +
                    JsExpression.class.getSimpleName() + " must not be null.");
        }
    }

    @Override
    public boolean isValid(Object bean, ConstraintValidatorContext context) {
        if (bean == null) {
            return true;
        }

        SimpleScriptContext scriptContext = new SimpleScriptContext();
        scriptContext.setAttribute(ROOT_BEAN_NAME, bean, ScriptContext.ENGINE_SCOPE);

        boolean valid;

        try {
            Object result = ENGINE.eval(expressionText, scriptContext);

            if (result instanceof Boolean) {
                valid = (Boolean) result;
            } else {
                throw new SystemIntegrityException("Result type is not boolean");
            }
        } catch (ScriptException e) {
            throw new SystemIntegrityException("Fail to execute java script [" + expressionText + "] in validator", e);
        }

        if ((!valid) && (StringUtils.isNotEmpty(annotation.subpath()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(annotation.message())
                    .addNode(annotation.subpath())
                    .addConstraintViolation();
        }

        return valid;
    }

}
