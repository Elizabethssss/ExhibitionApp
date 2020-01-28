package model.db.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements GenericDao<T> {
    private Connection connection;
    static final Logger logger = Logger.getLogger(AbstractDao.class);

    protected abstract String getInsertQuery();
    protected abstract String getSelectQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs);
    protected abstract void setData(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object);
    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object);

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

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
    public boolean delete(T object) {
        String sql = getDeleteQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            prepareStatementForDelete(ps, object);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Error in deleting from db", e);
        }
        return false;
    }

    @Override
    public Optional<T> getById(long id) {
        List<T> list = null;
        String sql = getSelectQuery();
        sql += " WHERE id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error in getting by id from db", e);
        }
        if (list == null || list.isEmpty()) {
            return Optional.empty();
        }

        return (Optional<T>) list.iterator().next();
    }

    @Override
    public List<T> getAll() {
        List<T> list = null;
        String sql = getSelectQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error in getting all in db", e);
        }
        return list;
    }

}
