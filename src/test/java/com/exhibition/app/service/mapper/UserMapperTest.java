package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.User;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.service.PasswordEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {
    private static final Long ID = 1L;
    private static final String USERNAME ="Vasya";
    private static final String EMAIL ="vasilii@kiev.ua";
    private static final String PASSWORD ="123";
    private static final Integer ROLE_ID = 1;

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private PasswordEncryptor passwordEncryptor;

    @Before
    public void init() {
        when(passwordEncryptor.encrypt(eq(PASSWORD))).thenReturn(PASSWORD);
    }

    @Test
    public void mapEntityToDomainShouldReturnUser() {
        final UserEntity userEntity = UserEntity.builder()
                .withId(ID)
                .withUsername(USERNAME)
                .withEmail(EMAIL)
                .withPassword(PASSWORD)
                .withRoleId(ROLE_ID)
                .build();

        final User user = userMapper.mapEntityToDomain(userEntity);
        assertThat("Mapping id is failed", user.getId(), is(ID));
        assertThat("Mapping username is failed", user.getUsername(), is(USERNAME));
        assertThat("Mapping email is failed", user.getEmail(), is(EMAIL));
        assertThat("Mapping password is failed", user.getPassword(), is(PASSWORD));
        assertThat("Mapping roles is failed", user.getRoleId(), is(ROLE_ID));
    }

    @Test
    public void mapDomainToEntityShouldReturnUserEntity() {
        final User user = User.builder()
                .withId(ID)
                .withUsername(USERNAME)
                .withEmail(EMAIL)
                .withPassword(PASSWORD)
                .withRoleId(ROLE_ID)
                .build();

        final UserEntity userEntity = userMapper.mapDomainToEntity(user);
        assertThat("Mapping id is failed", userEntity.getId(), is(ID));
        assertThat("Mapping username is failed", userEntity.getUsername(), is(USERNAME));
        assertThat("Mapping email is failed", userEntity.getEmail(), is(EMAIL));
        assertThat("Mapping password is failed", userEntity.getPassword(), is(PASSWORD));
        assertThat("Mapping roles is failed", userEntity.getRoleId(), is(ROLE_ID));
    }
}