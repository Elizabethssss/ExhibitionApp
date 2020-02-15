package com.exhibition.app.service.validator;

import com.exhibition.app.domain.User;
import com.exhibition.app.exception.FailException;
import com.exhibition.app.service.validator.impl.UserValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserValidatorImplTest {
    private final UserValidatorImpl userValidatorImpl = new UserValidatorImpl();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validateShouldNotThrowException() {
        final User user = initUser("Lolitta", "lol@kiev.ua");
        userValidatorImpl.validate(user);
    }

    @Test
    public void validateShouldThrowExceptionForNullEmail() {
        expectedException.expect(FailException.class);

        final User user = initUser("Lolitta", null);
        userValidatorImpl.validate(user);

    }

    @Test
    public void validateShouldThrowExceptionForNullUsername() {
        expectedException.expect(FailException.class);

        final User user = initUser( null, "");
        userValidatorImpl.validate(user);
    }

    @Test
    public void validateShouldThrowExceptionForInvalidUserEmail(){
        expectedException.expect(FailException.class);
        final User user = initUser("Lolitta","email");

        userValidatorImpl.validate(user);
    }

    private static User initUser(String username, String email) {
        return User.builder()
                .withUsername(username)
                .withEmail(email)
                .build();
    }
}