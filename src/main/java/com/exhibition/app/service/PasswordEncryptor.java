package com.exhibition.app.service;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryptor {
    public  String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }
}
