package com.exhibition.app.service;

import com.exhibition.app.entity.UserEntity;

public interface UserService {
    void register(UserEntity userEntity);
    boolean login(String username, String password);
}
