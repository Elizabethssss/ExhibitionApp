package com.exhibition.app.service.impl;

import com.exhibition.app.dao.UserDao;
import com.exhibition.app.domain.User;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.service.UserService;
import com.exhibition.app.service.mapper.Mapper;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Mapper<UserEntity, User> userMapper;

    public UserServiceImpl(UserDao userDao, Mapper<UserEntity, User> userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public void register(User user) {
        userDao.save(userMapper.mapDomainToEntity(user));
    }

    @Override
    public boolean login(String email, String password) {
        Optional<User> temp = userDao.findByEmail(email).map(userMapper::mapEntityToDomain);
        return temp.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    @Override
    public Optional<User> isUserExist(String email) {
        Optional<UserEntity> temp = userDao.findByEmail(email);
        return temp.map(userMapper::mapEntityToDomain);
    }
}
