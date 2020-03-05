package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.UserDao;
import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.exception.DataBaseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserDaoImplTest {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";

    private HikariCPManager manager;
    private UserEntity userEntity;
    private UserEntity testUserEntity;
    private UserDao userDao;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        try {
            manager = new HikariCPManager(DB_PROP_PATH);
            Connection connection = manager.getConnection();
            Statement statement = connection.createStatement();
            String schemaQuery = new String(Files.readAllBytes(Paths.get(SCHEMA_PATH)));
            statement.execute(schemaQuery);
            String dataQuery = new String(Files.readAllBytes(Paths.get(DATA_PATH)));
            statement.execute(dataQuery);
            statement.close();
            connection.close();
            userDao = new UserDaoImpl(manager);
            setEntities();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void shutDown() {
        manager.shutdown();
    }

    @Test
    public void findByEmailShouldReturnOptionalUserEntity() {
        assertTrue(userDao.findByEmail(userEntity.getEmail()).isPresent());
    }

    @Test
    public void findByEmailShouldReturnOptionalEmpty() {
        assertFalse(userDao.findByEmail(testUserEntity.getEmail()).isPresent());
    }

    @Test
    public void findByUsernameShouldReturnOptionalUserEntity() {
        assertTrue(userDao.findByUsername(userEntity.getUsername()).isPresent());
    }

    @Test
    public void findByUsernameShouldReturnOptionalEmpty() {
        assertFalse(userDao.findByUsername(testUserEntity.getUsername()).isPresent());
    }

    @Test
    public void saveUserShouldReturnTrue() {
        assertTrue(userDao.save(testUserEntity));
    }

    @Test
    public void saveUserShouldThrowException() {
        exception.expect(DataBaseException.class);
        assertFalse(userDao.save(userEntity));
    }

    @Test
    public void findByIdShouldReturnOptionalUserEntity() {
        assertTrue(userDao.findById(userEntity.getId()).isPresent());
    }

    @Test
    public void findByIdShouldReturnOptionalEmpty() {
        assertFalse(userDao.findById(testUserEntity.getId()).isPresent());
    }

    @Test
    public void updateShouldReturnTrue() {
        assertTrue(userDao.update(userEntity));
    }

    @Test
    public void updateShouldReturnFalse() {
        assertFalse(userDao.update(testUserEntity));
    }

//    @Test
//    public void updateShouldThrowException() {
//        exception.expect(DataBaseException.class);
//        assertFalse();
//    }

    @Test
    public void deleteShouldReturnTrue() {
        assertTrue(userDao.delete(userEntity.getId()));
    }

    @Test
    public void deleteShouldReturnFalse() {
        assertFalse(userDao.delete(0));
    }

    private void setEntities() {
        userEntity = UserEntity.builder()
                .withId(2L)
                .withUsername("Lizo4ka")
                .withEmail("liza@kiev.ua")
                .withPassword("24f6e3dc1bbc5a5dfb1e5c6481b94eb7")
                .withRoleId(0)
                .build();
        testUserEntity = UserEntity.builder()
                .withId(5L)
                .withUsername("Dima")
                .withEmail("dimusia@gmail.com")
                .withPassword("dima")
                .withRoleId(0)
                .build();
    }
}