package model.db.dao.impl;

import model.db.dao.AbstractDao;
import model.db.dao.Page;
import model.db.dao.PageableDao;
import model.db.dao.UserExhibDao;
import model.db.entity.Exhibition;
import model.db.entity.UserExhib;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utility.CollectionUtility.nullSafeListInitialize;

public class UserExhibDaoImpl extends AbstractDao<UserExhib> implements UserExhibDao, PageableDao<UserExhib> {
    private static final Logger logger = Logger.getLogger(UserExhibDaoImpl.class);

    public UserExhibDaoImpl(Connection connection) {
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
        return "UPDATE user_exhib SET is_bought = ? WHERE user_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM user_exhib WHERE id = ?;";
    }

    @Override
    protected String getFindByParamQuery() {
        return "SELECT * FROM user_exhib WHERE user_id = ? AND is_bought = ?;";
    }

    private String getSelectPaginationQuery() {
        return "SELECT * FROM user_exhib WHERE user_id = ? AND is_bought = 1 LIMIT ? OFFSET ?;";
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
    protected void prepareData(PreparedStatement statement, UserExhib object) throws SQLException {
        statement.setLong(1, object.getUserId());
        statement.setLong(2, object.getExhibitionId());
        statement.setBoolean(3, object.isBought());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserExhib object) {
        try {
            prepareData(statement, object);
        } catch (SQLException e) {
            logger.error("Can't prepare user_exhib statement for insert", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserExhib object) {
        try {
            statement.setBoolean(1, object.isBought());
            statement.setLong(2, object.getUserId());
        } catch (SQLException e) {
            logger.error("Can't prepare user_exhib statement for update", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, long id) {
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            logger.error("Can't prepare user_exhib statement for delete", e);
        }
    }

    @Override
    public List<UserExhib> getExhibsByUserIdAndIsBought(long userId, boolean isBought, Connection connection) {
        List<UserExhib> list = nullSafeListInitialize(null);
        String sql = getFindByParamQuery();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setBoolean(2, isBought);
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error in getting exhibs by user id and isBought from db", e);
        }
        return list;
    }

    @Override
    public List<UserExhib> findAll(long id, Page page, Connection connection) {
        List<UserExhib> list = nullSafeListInitialize(null);
        String sql = getSelectPaginationQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.setLong(2, page.getRecordNumber());
            ps.setLong(3, page.getPageNumber());
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error in getting all in db", e);
        }
        return list;
    }
}
