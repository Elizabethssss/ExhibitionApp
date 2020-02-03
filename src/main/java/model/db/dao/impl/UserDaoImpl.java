package model.db.dao.impl;

import model.db.dao.AbstractDao;
import model.db.dao.GenericDao;
import model.db.dao.UserDao;
import model.db.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public static final String CHECK_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?;";
    public static final String CHECK_PASSWORD_QUERY = "SELECT * FROM user WHERE password = ?;";

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
    protected String getFindByParamQuery() {
        return "SELECT * FROM user WHERE username = ?;";
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
    protected void prepareData(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getUsername());
        statement.setString(2, object.getEmail());
        statement.setString(3, object.getPassword());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) {
        try {
            prepareData(statement, object);
        } catch (SQLException e) {
            logger.error("Can't prepare user statement for insert", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) {
        try {
            prepareData(statement, object);
            statement.setLong(4, object.getId());
        } catch (SQLException e) {
            logger.error("Can't prepare user statement for update", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, long id) {
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            logger.error("Can't prepare user statement for delete", e);
        }
    }

    @Override
    public boolean checkUsername(String username, Connection connection) {
        String sql = getFindByParamQuery();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error in checking username from db", e);
        }
        return false;
    }

    @Override
    public boolean checkEmail(String email, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(CHECK_EMAIL_QUERY)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error in checking email from db", e);
        }
        return false;
    }

    @Override
    public boolean checkPassword(String password, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(CHECK_PASSWORD_QUERY)) {
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error in checking password from db", e);
        }
        return false;
    }
}
