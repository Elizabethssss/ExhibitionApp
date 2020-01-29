package model.db.service;

import model.db.dao.AbstractDao;
import model.db.entity.UserExhib;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserExhibDao extends AbstractDao<UserExhib> {
    static final Logger logger = Logger.getLogger(MySqlUserExhibDao.class);

    public MySqlUserExhibDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO user_exhib VALUES (DEFAULT, ?, ?, ?);";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM user_exhib";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE user_exhib SET user_id = ?, exhibition_id = ?, is_bought = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM user_exhib WHERE id = ?;";
    }

    @Override
    protected List<UserExhib> parseResultSet(ResultSet rs) {
        List<UserExhib> result = new ArrayList<>();
        try {
            while (rs.next()) {
                UserExhib temp = new UserExhib();
                temp.setId(rs.getLong("id"));
                temp.setUserId(rs.getLong("user_id"));
                temp.setExhibitionId(rs.getLong("exhibition_id"));
                temp.setBought(rs.getBoolean("is_bought"));
                result.add(temp);
            }
        } catch (SQLException e) {
            logger.error("Can't parse user_exhib result set", e);
        }
        return result;
    }

    @Override
    protected void setData(PreparedStatement statement, UserExhib object) throws SQLException {
        statement.setLong(1, object.getUserId());
        statement.setLong(2, object.getExhibitionId());
        statement.setBoolean(3, object.isBought());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserExhib object) {
        try {
            setData(statement, object);
        } catch (SQLException e) {
            logger.error("Can't prepare user_exhib statement for insert", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserExhib object) {
        try {
            setData(statement, object);
        } catch (SQLException e) {
            logger.error("Can't prepare user_exhib statement for update", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, UserExhib object) {
        try {
            statement.setLong(1, object.getId());
        } catch (SQLException e) {
            logger.error("Can't prepare user_exhib statement for delete", e);
        }
    }
}
