package model.jdbc.dao;

import model.jdbc.entity.Exhibition;
import model.jdbc.entity.UserExhib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements GenericDao<T> {
    private Connection connection;

    protected abstract String getInsertQuery();
    protected abstract String getSelectQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs);
    protected abstract void setData(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object);
    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object);

    @Override
    public boolean add(T object) {
        String sql = getInsertQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            prepareStatementForInsert(ps, object);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public T getById(long id) {
        List<T> list = null;
        String sql = getSelectQuery();
        sql += " WHERE id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list == null || list.isEmpty())
            return null;

        return list.iterator().next();
    }

    @Override
    public List<T> getAll() {
        List<T> list = null;
        String sql = getSelectQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Exhibition> getListOfExhibByUserId(long id, Class<?> clazz) {
        if(clazz == UserExhib.class) {
            List<Exhibition> list = new ArrayList<>();
            String sql = getSelectQuery();
            sql += "WHERE user_id = ?;";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                list = (List<Exhibition>) parseResultSet(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
        else return null;
    }

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}
