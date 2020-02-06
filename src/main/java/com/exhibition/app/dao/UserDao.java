package com.exhibition.app.dao;

import com.exhibition.app.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends GenericDao<UserEntity> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}
