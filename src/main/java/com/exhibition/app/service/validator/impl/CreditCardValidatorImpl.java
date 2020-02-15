package com.exhibition.app.service.validator.impl;

import com.exhibition.app.service.validator.CreditCardValidator;

import java.util.Calendar;
import java.util.Date;

public class CreditCardValidatorImpl implements CreditCardValidator {
    private static final String CARD_NUMBER_INPUT = "[0-9]{13,16}";
    private static final String CARD_CVV_INPUT = "[0-9]{3}";
    private static final String CARD_MONTH_INPUT = "(0[1-9]|1[0-2])";
    private static final String CARD_YEAR_INPUT = "([2-3][0-9])";

    @Override
    public boolean validateNumberInput(String number) {
        return number.matches(CARD_NUMBER_INPUT);
    }

    @Override
    public boolean validateCVVInput(String cvv) {
        return cvv.matches(CARD_CVV_INPUT);
    }

    @Override
    public boolean validateDateInput(String month, String year) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        calendar.set(Integer.parseInt(year) + 2000, Integer.parseInt(month) - 1, 1);
        Date myDate = calendar.getTime();
        return validateMonthInput(month) && validateYearInput(year) && date.compareTo(myDate) < 0;
    }

    public boolean validateMonthInput(String month) {
        return month.matches(CARD_MONTH_INPUT);
    }

    public boolean validateYearInput(String year) {
        return year.matches(CARD_YEAR_INPUT);
    }

    @Override
    public boolean validateCreditCardNumber(String number) {
        int[] ints = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            ints[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i -= 2) {
            int j = ints[i];
            j *= 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        return sum % 10 == 0;
    }
}
