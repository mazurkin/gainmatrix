package com.gainmatrix.lib.validation.validators.js;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Expression validator based on JavaScript language
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = JsExpressionValidator.class)
@Documented
public @interface JsExpression {

    String message() default "{com.gainmatrix.validator.constraints.Js.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String value();

    String subpath() default "";

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        JsExpression[] value();
    }

}
