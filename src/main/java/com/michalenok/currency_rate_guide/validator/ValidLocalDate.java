package com.michalenok.currency_rate_guide.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER, TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = LocalDateValidator.class)
@Documented
public @interface ValidLocalDate {
    String message() default "Invalid date format (acceptable format: [yyyy-MM-dd])";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}