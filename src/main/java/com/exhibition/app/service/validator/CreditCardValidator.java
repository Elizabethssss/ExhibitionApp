package com.exhibition.app.service.validator;

public interface CreditCardValidator {
    boolean validateCreditCardNumber(String number);
    boolean validateNumberInput(String number);
    boolean validateCVVInput(String cvv);
    boolean validateDateInput(String month, String year);
}
