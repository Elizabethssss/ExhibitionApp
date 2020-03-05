package com.exhibition.app.service.validator.impl;

import com.exhibition.app.service.validator.CreditCardValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCardValidatorImplTest {
    public static final String RIGHT_CARD_NUMBER = "378282246310005";
    public static final String RIGHT_CVV = "378";
    public static final String RIGHT_MONTH = "03";
    public static final String RIGHT_YEAR = "24";

    private CreditCardValidator validator = new CreditCardValidatorImpl();

    @Test
    public void validateNumberInputShouldReturnTrue() {
        assertTrue(validator.validateNumberInput(RIGHT_CARD_NUMBER));
    }

    @Test
    public void validateNumberInputShouldReturnFalse() {
        assertFalse(validator.validateNumberInput(RIGHT_CARD_NUMBER + "a"));
    }

    @Test
    public void validateCVVInputShouldReturnTrue() {
        assertTrue(validator.validateCVVInput(RIGHT_CVV));
    }

    @Test
    public void validateCVVInputShouldReturnFalse() {
        assertFalse(validator.validateCVVInput(RIGHT_CVV + "a"));
    }

    @Test
    public void validateDateInputShouldReturnTrue() {
        assertTrue(validator.validateDateInput(RIGHT_MONTH, RIGHT_YEAR));
    }

    @Test
    public void validateDateInputShouldReturnFalse() {
        assertFalse(validator.validateDateInput("1", "19"));
        assertFalse(validator.validateDateInput("1", "24"));
        assertFalse(validator.validateDateInput("1", "19"));
        assertFalse(validator.validateDateInput("01", "20"));
        assertFalse(validator.validateDateInput("01", "19"));
    }

    @Test
    public void validateCreditCardNumberShouldReturnTrue() {
        assertTrue(validator.validateCreditCardNumber(RIGHT_CARD_NUMBER));
    }

    @Test
    public void validateCreditCardNumberShouldReturnFalse() {
        assertFalse(validator.validateCreditCardNumber(RIGHT_CARD_NUMBER + 0));
    }

}