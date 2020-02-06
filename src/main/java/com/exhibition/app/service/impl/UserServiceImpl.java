package com.exhibition.app.service.impl;

import com.exhibition.app.dao.UserDao;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.exception.AuthorisationFailException;
import com.exhibition.app.exception.EntityAlreadyExistException;
import com.exhibition.app.service.UserService;
import com.exhibition.app.service.validator.Validator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Validator<UserEntity> validator;

    public UserServiceImpl(UserDao userDao, Validator<UserEntity> validator) {
        this.userDao = userDao;
        this.validator = validator;
    }

    @Override
    public void register(UserEntity userEntity) {
        validator.validate(userEntity);

        Optional<UserEntity> temp = userDao.findByUsername(userEntity.getUsername());
        Optional<UserEntity> temp1 = userDao.findByEmail(userEntity.getEmail());
        if (temp.isPresent()) {
            throw new EntityAlreadyExistException(401);
        }
        else if(temp1.isPresent()) {
            throw new EntityAlreadyExistException(402);
        }
        else {
            userDao.add(userEntity);
        }
    }

    @Override
    public boolean login(String username, String password) {
        Optional<UserEntity> temp = userDao.findByUsername(username);
        if(temp.isEmpty()) {
            throw new AuthorisationFailException(401);
        }
        else if(temp.get().getPassword().equals(password)) {
            return true;
        }
        else {
            throw new AuthorisationFailException(401);
        }
    }
}
