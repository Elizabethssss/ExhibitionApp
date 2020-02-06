package com.exhibition.app.service.validator;

import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.exception.InvalidUserInputException;

public class UserValidator implements Validator<UserEntity> {
    private static final String EN_INPUT_PATTERN = "(\\w|\\$|@)+";
    private static final String EMAIL_PATTERN = "(\\w+\\.?)+@(\\w+\\.?)+";

    @Override
    public void validate(UserEntity userEntity) {
        if(!userEntity.getUsername().matches(EN_INPUT_PATTERN)) {
            throw new InvalidUserInputException(401);
        }
        else if(!userEntity.getEmail().matches(EMAIL_PATTERN)) {
            throw new InvalidUserInputException(402);
        }
        else if(!userEntity.getEmail().matches(EN_INPUT_PATTERN)) {
            throw new InvalidUserInputException(403);
        }
    }
}
