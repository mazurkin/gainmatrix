package com.gainmatrix.lib.validation.validators.spel;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Expression validator based on Spring Framework expression language (SPEL)
 * @see <a href="http://static.springsource.org/spring/docs/3.0.x/reference/expressions.html">SPEL documentation</a>
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpelExpressionValidator.class)
@Documented
public @interface SpelExpression {

    String message() default "{com.gainmatrix.validator.constraints.Spel.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String value();

    String subpath() default "";

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        SpelExpression[] value();
    }

}
