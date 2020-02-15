package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.ExpositionDao;
import com.exhibition.app.dao.Page;
import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.entity.ExpositionEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExpositionDaoImpl extends AbstractDao<ExpositionEntity> implements ExpositionDao {
    public static final String SAVE_QUERY = "INSERT INTO exposition VALUES (DEFAULT, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE exposition SET name = ?, about = ?, image = ?," +
            " exhib_id = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM exposition WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM exposition WHERE id = ?;";
    public static final String FIND_BY_EXHIBITION_ID_QUERY = "SELECT * FROM exposition WHERE exhib_id = ?;";
    public static final String FIND_PAGE_BY_EXHIBITION_ID_QUERY = "SELECT * FROM exposition " +
            "WHERE exhib_id = ? LIMIT ? OFFSET ?;";
    public static final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM exposition WHERE exhib_id = ?;";

    public ExpositionDaoImpl(HikariCPManager connector) {
        super(connector, SAVE_QUERY, UPDATE_QUERY, DELETE_QUERY, FIND_BY_ID_QUERY);
    }

    @Override
    public List<ExpositionEntity> findAll(Long id, Page page) {
        return findAll(id, page, FIND_PAGE_BY_EXHIBITION_ID_QUERY);
    }

    @Override
    public int count(Long id) {
       return count(id, COUNT_ALL_QUERY);
    }

    @Override
    public Optional<ExpositionEntity> findByExhibitionId(long exhibitionId) {
        return findByParam(exhibitionId, FIND_BY_EXHIBITION_ID_QUERY);
    }

    @Override
    protected ExpositionEntity parseResultSet(ResultSet rs) throws SQLException {
        return ExpositionEntity.builder()
                .withId(rs.getLong("id"))
                .withName(rs.getString("name"))
                .withAbout(rs.getString("about"))
                .withImage(rs.getString("image"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, ExpositionEntity object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getAbout());
        statement.setString(3, object.getImage());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ExpositionEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(4, object.getId());
    }
}
