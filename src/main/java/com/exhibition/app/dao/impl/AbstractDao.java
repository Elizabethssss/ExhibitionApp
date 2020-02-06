package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.dao.GenericDao;
import com.exhibition.app.dao.Page;
import com.exhibition.app.exception.DataBaseException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements GenericDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);

    private final HikariCPManager connector;
    private final String saveQuery;
    private final String updateQuery;
    private final String deleteQuery;
    private final String findByIdQuery;

    public AbstractDao(HikariCPManager connector, String saveQuery, String updateQuery, String deleteQuery,
                       String findByIdQuery) {
        this.connector = connector;
        this.saveQuery = saveQuery;
        this.updateQuery = updateQuery;
        this.deleteQuery = deleteQuery;
        this.findByIdQuery = findByIdQuery;
    }

    public HikariCPManager getConnector() {
        return connector;
    }

    @Override
    public boolean save(T object) {
        try(Connection connection = connector.getConnection();
            PreparedStatement ps = connection.prepareStatement(saveQuery)) {
            prepareStatementForInsert(ps, object);
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error in adding to db", e);
            throw new DataBaseException("Error in adding to db", e);
        }
        return false;
    }

    @Override
    public boolean update(T object) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {
            prepareStatementForUpdate(ps, object);
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error in updating db", e);
            throw new DataBaseException("Error in updating db", e);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        try(Connection connection = connector.getConnection();
            PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
            ps.setLong(1, id);
            if(id != 0 && ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error in deleting from db", e);
            throw new DataBaseException("Error in deleting from db", e);
        }
        return false;
    }

    @Override
    public Optional<T> findById(long id) {
        return findByParam(id, findByIdQuery);
    }

    protected <P> Optional<T> findByParam(P param, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, param);
            try(final ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return Optional.ofNullable(parseResultSet(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting by parameter from db", e);
            throw new DataBaseException("Error in getting by parameter from db", e);
        }
        return Optional.empty();
    }

    protected List<T> findAll(Page page, String query) {
        try(Connection connection = connector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, page.getPageNumber());
            ps.setInt(2, page.getRecordNumber());
            try(final ResultSet rs = ps.executeQuery()) {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(parseResultSet(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            logger.error("Error in getting all from db", e);
            throw new DataBaseException("Error in getting all from db", e);
        }
    }


    protected abstract T parseResultSet(ResultSet resultSet) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement preparedStatement, T object) throws SQLException;
    protected abstract void prepareStatementForUpdate(PreparedStatement preparedStatement, T object) throws SQLException;
}
