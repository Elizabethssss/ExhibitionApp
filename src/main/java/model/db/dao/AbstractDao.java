package model.db.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static utility.CollectionUtility.nullSafeListInitialize;

public abstract class AbstractDao<T> implements GenericDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    private Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected abstract String getInsertQuery();
    protected abstract String getSelectQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getFindByParamQuery();

    protected abstract List<T> parseResultSet(ResultSet rs);

    protected abstract void prepareData(PreparedStatement statement, T object) throws SQLException;
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object);
    protected abstract void prepareStatementForDelete(PreparedStatement statement, long id);

    @Override
    public boolean add(T object) {
        String sql = getInsertQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            prepareStatementForInsert(ps, object);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Error in adding to db", e);
        }
        return false;
    }

    @Override
    public boolean update(T object) {
        String sql = getUpdateQuery();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(ps, object);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Error in updating db", e);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        String sql = getDeleteQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            prepareStatementForDelete(ps, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Error in deleting from db", e);
        }
        return false;
    }

    @Override
    public Optional<T> getById(long id) {
        List<T> list = nullSafeListInitialize(null);
        String sql = getSelectQuery();
        sql += " WHERE id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error in getting by id from db", e);
        }

        return Optional.ofNullable(list.iterator().next());
    }

    @Override
    public List<T> getAll() {
        List<T> list = nullSafeListInitialize(null);
        String sql = getSelectQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error in getting all in db", e);
        }
        return list;
    }

    @Override
    public List<T> findByParam(Object param) {
        List<T> list = nullSafeListInitialize(null);
        String sql = getFindByParamQuery();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, param);
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error in getting by parameter from db", e);
        }

        return list;
    }

}
