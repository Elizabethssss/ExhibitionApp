package com.exhibition.app.service.validator;

public interface Validator<T> {
    void validate(T object);
}
