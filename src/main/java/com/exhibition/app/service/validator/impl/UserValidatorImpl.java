package com.exhibition.app.service.validator.impl;

import com.exhibition.app.domain.User;
import com.exhibition.app.exception.ErrorTypes;
import com.exhibition.app.exception.FailException;
import com.exhibition.app.service.validator.UserValidator;

import java.util.Optional;

public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_PATTERN = "(\\w+\\.?)+@(\\w+\\.?)+";

    @Override
    public void validate(User user) {
        final Optional<User> optionalUser = Optional.ofNullable(user);
        optionalUser.map(User::getEmail).filter(x -> x.matches(EMAIL_PATTERN)).orElseThrow(() ->
                new FailException(ErrorTypes.EMAIL_INPUT_ERROR));
    }
}
