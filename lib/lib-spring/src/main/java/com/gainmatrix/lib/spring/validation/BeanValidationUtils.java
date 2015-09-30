package com.gainmatrix.lib.spring.validation;

import com.google.common.base.Preconditions;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Static helper validating functions
 */
public final class BeanValidationUtils {

    private BeanValidationUtils() {
    }

    /**
     * Check is bean valid with the specified validator. If bean is not valid an exception is thrown with detailed
     * error description
     * @param bean Bean to validate
     * @param validator Validator
     * @throws IllegalArgumentException Exception is thrown if bean is not valid
     */
    public static void checkValidity(Object bean, Validator validator) {
        Preconditions.checkNotNull(bean, "Bean is null");
        Preconditions.checkNotNull(validator, "Validator is null");

        if (!validator.supports(bean.getClass())) {
            throw new IllegalStateException("Validator doesn't support class : " + bean.getClass());
        }

        Errors errors = new BeanPropertyBindingResult(bean, "bean");
        validator.validate(bean, errors);

        if (errors.hasErrors()) {
            String message = String.format(
                "Bean (%s) failed to pass bean validator with the following constraints (%s)",
                bean.toString(),
                errors.toString()
            );

            throw new IllegalArgumentException(message);
        }
    }

}
