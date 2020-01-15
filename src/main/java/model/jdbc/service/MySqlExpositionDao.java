package model.jdbc.service;

import model.jdbc.dao.AbstractDao;
import model.jdbc.entity.Exhibition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpositionDao extends AbstractDao<Exhibition.Exposition> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO exposition (name, about, image, exhib_id)\n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM exposition";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE exposition SET name = ?, about = ?, image = ?, exhib_id = ?\n" +
                "WHERE id= ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM exposition WHERE id= ?;";
    }

    @Override
    protected List<Exhibition.Exposition> parseResultSet(ResultSet rs) {
        List<Exhibition.Exposition> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Exhibition.Exposition temp = new Exhibition.Exposition();
                temp.setId(rs.getLong("id"));
                temp.setName(rs.getString("name"));
                temp.setAbout(rs.getString("about"));
                temp.setImage(rs.getString("image"));
                temp.setExhibId(rs.getLong("exhib_id"));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void setData(PreparedStatement statement, Exhibition.Exposition object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getAbout());
        statement.setString(3, object.getImage());
        statement.setLong(4, object.getExhibId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Exhibition.Exposition object) {
        try {
            setData(statement, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Exhibition.Exposition object) {
        try {
            setData(statement, object);
            statement.setLong(5, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Exhibition.Exposition object) {
        try {
            statement.setLong(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MySqlExpositionDao(Connection connection) {
        super(connection);
    }
}
