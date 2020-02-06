package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.dao.Page;
import com.exhibition.app.dao.TicketDao;
import com.exhibition.app.entity.TicketEntity;
import com.exhibition.app.exception.DataBaseException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl extends AbstractDao<TicketEntity> implements TicketDao {
    private static final Logger logger = Logger.getLogger(TicketDaoImpl.class);

    public static final String SAVE_QUERY = "INSERT INTO ticket VALUES (DEFAULT, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE ticket SET is_bought = ? WHERE user_id = ?";
    public static final String DELETE_QUERY = "DELETE FROM ticket WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM ticket WHERE id = ?;";
    public static final String FIND_BY_USER_ID_AND_BOUGHT_QUERY = "SELECT * FROM ticket WHERE user_id = ? AND is_bought = ?;";
    public static final String FIND_PAGE_BY_USER_ID_QUERY = "SELECT * FROM ticket " +
            "WHERE user_id = ? AND is_bought = 1 LIMIT ? OFFSET ?;";

    public TicketDaoImpl(HikariCPManager connector) {
        super(connector, SAVE_QUERY, UPDATE_QUERY, DELETE_QUERY, FIND_BY_ID_QUERY);
    }


    @Override
    public List<TicketEntity> findByUserIdAndIsBought(Long userId, boolean isBought) {
        try(Connection connection = getConnector().getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_BY_USER_ID_AND_BOUGHT_QUERY)) {
            ps.setLong(1, userId);
            ps.setBoolean(2, isBought);
            try(final ResultSet rs = ps.executeQuery()) {
                List<TicketEntity> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(parseResultSet(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            logger.error("Error in getting by user id and is bought from db", e);
            throw new DataBaseException("Error in getting by user id and is bought from db", e);
        }
    }

    @Override
    public List<TicketEntity> findAll(Page page) {
        return findAll(page, FIND_PAGE_BY_USER_ID_QUERY);
    }

    @Override
    protected TicketEntity parseResultSet(ResultSet rs) throws SQLException {
        return TicketEntity.builder()
                .withId(rs.getLong("id"))
                .withUserId(rs.getLong("user_id"))
                .withExhibitionId(rs.getLong("exhibition_id"))
                .withIsBought(rs.getBoolean("is_bought"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, TicketEntity object) throws SQLException {
        preparedStatement.setLong(1, object.getUserId());
        preparedStatement.setLong(2, object.getExhibitionId());
        preparedStatement.setBoolean(3, object.isBought());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, TicketEntity object) throws SQLException {
        preparedStatement.setBoolean(1, object.isBought());
        preparedStatement.setLong(2, object.getUserId());
    }
}
