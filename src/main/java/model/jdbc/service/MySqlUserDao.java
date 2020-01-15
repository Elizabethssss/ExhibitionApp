package model.jdbc.service;

import model.jdbc.dao.AbstractDao;
import model.jdbc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends AbstractDao<User> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO user (username, email, password)\n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM user";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE user SET username = ?, email = ?, password = ?\n" +
                "WHERE id= ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM user WHERE id= ?;";
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) {
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()) {
                User temp = new User();
                temp.setId(rs.getLong("id"));
                temp.setUsername(rs.getString("username"));
                temp.setEmail(rs.getString("email"));
                temp.setPassword(rs.getString("password"));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void setData(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getUsername());
        statement.setString(2, object.getEmail());
        statement.setString(3, object.getPassword());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) {
        try {
            setData(statement, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) {
        try {
            setData(statement, object);
            statement.setLong(4, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, User object) {
        try {
            statement.setLong(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MySqlUserDao(Connection connection) {
        super(connection);
    }
}
