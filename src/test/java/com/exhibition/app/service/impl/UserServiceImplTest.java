package com.exhibition.app.service.impl;

import com.exhibition.app.dao.UserDao;
import com.exhibition.app.domain.User;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.service.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    public static final User USER = initUser();
    public static final UserEntity USER_ENTITY = initEntityUser();

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void init() {
        when(userMapper.mapDomainToEntity(USER)).thenReturn(USER_ENTITY);
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER);
    }

    @Test
    public void registerShouldBeSuccessful() {
        when(userDao.save(USER_ENTITY)).thenReturn(true);

        userService.register(USER);

        verify(userDao).save(USER_ENTITY);
    }

    @Test
    public void loginShouldReturnTrue() {
        when(userDao.findByEmail(USER_ENTITY.getEmail())).thenReturn(Optional.of(USER_ENTITY));

        assertTrue(userService.login(USER_ENTITY.getEmail(), USER_ENTITY.getPassword()));

        verify(userDao).findByEmail(USER_ENTITY.getEmail());
    }

    @Test
    public void isUserExistShouldReturnTrue() {
        when(userDao.findByEmail(USER_ENTITY.getEmail())).thenReturn(Optional.of(USER_ENTITY));

        assertTrue(userService.isUserExist(USER_ENTITY.getEmail()).isPresent());

        verify(userDao).findByEmail(USER_ENTITY.getEmail());
    }

    private static User initUser() {
        return User.builder()
                .withId(1L)
                .withUsername("Liza")
                .withEmail("lizo4ka@gmail.com")
                .withPassword("123")
                .withRoleId(0)
                .build();
    }

    private static UserEntity initEntityUser() {
        return UserEntity.builder()
                .withId(1L)
                .withUsername("Liza")
                .withEmail("lizo4ka@gmail.com")
                .withPassword("123")
                .withRoleId(0)
                .build();
    }
}