package model.db.dao.impl;

import model.db.dao.AbstractDao;
import model.db.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    private static final Logger logger = Logger.getLogger(UserDao.class);

    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO user VALUES (DEFAULT, ?, ?, ?);";
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
            logger.error("Can't parse user result set", e);
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
            logger.error("Can't prepare user statement for insert", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) {
        try {
            setData(statement, object);
            statement.setLong(4, object.getId());
        } catch (SQLException e) {
            logger.error("Can't prepare user statement for update", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, User object) {
        try {
            statement.setLong(1, object.getId());
        } catch (SQLException e) {
            logger.error("Can't prepare user statement for delete", e);
        }
    }
}
