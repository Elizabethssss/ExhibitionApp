package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.ExhibitionDao;
import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.entity.ExhibitionEntity;
import com.exhibition.app.exception.DataBaseException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionDaoImpl extends AbstractDao<ExhibitionEntity> implements ExhibitionDao {
    private static final Logger logger = Logger.getLogger(ExhibitionDaoImpl.class);

    public static final String SAVE_QUERY = "INSERT INTO exhibition VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE exhibition SET name = ?, date_from = ?, date_to = ?," +
            " theme = ?, about = ?, long_about = ?, price = ?, image = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM exhibition WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM exhibition WHERE id = ?;";
    public static final String FIND_BY_DATE_LIKE_QUERY = "SELECT * FROM exhibition WHERE date_from LIKE ?;";

    public ExhibitionDaoImpl(HikariCPManager connector) {
        super(connector, SAVE_QUERY, UPDATE_QUERY, DELETE_QUERY, FIND_BY_ID_QUERY);
    }

    @Override
    public List<ExhibitionEntity> findByDateLike(String date) {
        try(Connection connection = getConnector().getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_BY_DATE_LIKE_QUERY)) {
            ps.setString(1, date);
            try(final ResultSet rs = ps.executeQuery()) {
                List<ExhibitionEntity> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(parseResultSet(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            logger.error("Error in finding by date like from db", e);
            throw new DataBaseException("Error in finding by date like from db", e);
        }
    }

    @Override
    protected ExhibitionEntity parseResultSet(ResultSet rs) throws SQLException {
        return ExhibitionEntity.builder()
                .withId(rs.getLong("id"))
                .withName(rs.getString("name"))
                .withDateFrom(rs.getDate("date_from"))
                .withDateTo(rs.getDate("date_to"))
                .withTheme(rs.getString("theme"))
                .withAbout(rs.getString("about"))
                .withLongAbout(rs.getString("long_about"))
                .withPrice(rs.getDouble("price"))
                .withImage(rs.getString("image"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, ExhibitionEntity object) throws SQLException{
        statement.setString(1, object.getName());
        statement.setDate(2, object.getDateFrom());
        statement.setDate(3, object.getDateTo());
        statement.setString(4, object.getTheme());
        statement.setString(5, object.getAbout());
        statement.setString(6, object.getLongAbout());
        statement.setDouble(7, object.getPrice());
        statement.setString(8, object.getImage());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ExhibitionEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(9, object.getId());
    }
}
