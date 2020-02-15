package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.User;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.service.PasswordEncryptor;

import java.util.Optional;

public class UserMapper implements Mapper<UserEntity, User> {
    private final PasswordEncryptor passwordEncryptor;

    public UserMapper(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public User mapEntityToDomain(UserEntity entity) {
        return User.builder()
                .withId(entity.getId())
                .withUsername(entity.getUsername())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .withRoleId(entity.getRoleId())
                .build();
    }

    @Override
    public UserEntity mapDomainToEntity(User user) {
        return UserEntity.builder()
                .withId(Optional.ofNullable(user.getId()).orElse(0L))
                .withUsername(user.getUsername())
                .withEmail(user.getEmail())
                .withPassword(passwordEncryptor.encrypt(user.getPassword()))
                .withRoleId(Optional.ofNullable(user.getRoleId()).orElse(0))
                .build();
    }
}
