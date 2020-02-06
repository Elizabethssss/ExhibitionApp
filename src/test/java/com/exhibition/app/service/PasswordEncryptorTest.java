package com.exhibition.app.service;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PasswordEncryptorTest {
    private PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

    @Test
    public void encrypt() {
        final String password = "password";
        final String encoded = passwordEncryptor.encrypt("liza");
        assertNotEquals(password, encoded);
    }
}