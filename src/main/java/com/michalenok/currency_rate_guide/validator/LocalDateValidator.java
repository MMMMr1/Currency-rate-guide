package com.michalenok.currency_rate_guide.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateValidator implements ConstraintValidator<ValidLocalDate, String> {
    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        return validateLocalDate(date);
    }

    private boolean validateLocalDate(String date) {
        if (date == null) return true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            formatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
