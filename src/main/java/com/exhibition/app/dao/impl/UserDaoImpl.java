package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.dao.UserDao;
import com.exhibition.app.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    public static final String SAVE_QUERY = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE user SET username = ?, email = ?, password = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM user WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM user WHERE id = ?;";
    public static final String FIND_BY_USERNAME_QUERY = "SELECT * FROM user WHERE username = ?;";
    public static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?;";

    public UserDaoImpl(HikariCPManager connector) {
        super(connector, SAVE_QUERY, UPDATE_QUERY, DELETE_QUERY, FIND_BY_ID_QUERY);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return findByParam(username, FIND_BY_USERNAME_QUERY);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findByParam(email, FIND_BY_EMAIL_QUERY);
    }

    @Override
    protected UserEntity parseResultSet(ResultSet rs) throws SQLException {
        return UserEntity.builder()
                .withId(rs.getLong("id"))
                .withUsername(rs.getString("username"))
                .withEmail(rs.getString("email"))
                .withPassword(rs.getString("password"))
               // .withRole((RoleEntity) rs.getObject("role_id"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserEntity object) throws SQLException {
        statement.setString(1, object.getUsername());
        statement.setString(2, object.getEmail());
        statement.setString(3, object.getPassword());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(4, object.getId());
    }
}
