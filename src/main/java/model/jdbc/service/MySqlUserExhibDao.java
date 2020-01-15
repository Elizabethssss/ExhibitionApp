package model.jdbc.service;

import model.jdbc.dao.AbstractDao;
import model.jdbc.entity.UserExhib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserExhibDao extends AbstractDao<UserExhib> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO user_exhib (user_id, exhibition_id)\n" +
                "VALUES (?, ?);";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM user_exhib";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE user_exhib SET user_id = ?, exhibition_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM user_exhib WHERE user_id = ? AND exhibition_id = ?;";
    }

    @Override
    protected List<UserExhib> parseResultSet(ResultSet rs) {
        List<UserExhib> result = new ArrayList<>();
        try {
            while (rs.next()) {
                UserExhib temp = new UserExhib();
                temp.setUserId(rs.getLong("user_id"));
                temp.setExhibitionId(rs.getLong("exhibition_id"));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void setData(PreparedStatement statement, UserExhib object) throws SQLException {
        statement.setLong(1, object.getUserId());
        statement.setLong(2, object.getExhibitionId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserExhib object) {
        try {
            setData(statement, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserExhib object) {
        try {
            setData(statement, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, UserExhib object) {
        try {
            statement.setLong(1, object.getUserId());
            statement.setLong(2, object.getExhibitionId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public MySqlUserExhibDao(Connection connection) {
        super(connection);
    }
}
