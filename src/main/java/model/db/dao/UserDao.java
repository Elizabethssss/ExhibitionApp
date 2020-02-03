package model.db.dao;

import model.db.entity.User;

import java.sql.Connection;

public interface UserDao extends GenericDao<User> {
    boolean checkUsername(String username, Connection connection);
    boolean checkEmail(String email, Connection connection);
    boolean checkPassword(String password, Connection connection);
}
