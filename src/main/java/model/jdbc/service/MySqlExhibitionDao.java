package model.jdbc.service;

import model.jdbc.dao.AbstractDao;
import model.jdbc.entity.Exhibition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExhibitionDao extends AbstractDao<Exhibition> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO exhibition VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM exhibition";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE exhibition SET name = ?, date = ?, theme = ?, about = ?, price = ?, image = ?\n" +
                "WHERE id= ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM exhibition WHERE id=?;";
    }

    @Override
    protected List<Exhibition> parseResultSet(ResultSet rs) {
        List<Exhibition> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Exhibition temp = new Exhibition();
                temp.setId(rs.getLong("id"));
                temp.setName(rs.getString("name"));
                temp.setDate(rs.getDate("date"));
                temp.setTheme(rs.getString("theme"));
                temp.setAbout(rs.getString("about"));
                temp.setPrice(rs.getDouble("price"));
                temp.setImage(rs.getString("image"));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Exhibition object) {
        try {
            setData(statement, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setData(PreparedStatement statement, Exhibition object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setDate(2, object.getDate());
        statement.setString(3, object.getTheme());
        statement.setString(4, object.getAbout());
        statement.setDouble(5, object.getPrice());
        statement.setString(6, object.getImage());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Exhibition object) {
        try {
            setData(statement, object);
            statement.setLong(7, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Exhibition object) {
        try {
            statement.setLong(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MySqlExhibitionDao(Connection connection) {
        super(connection);
    }
}
