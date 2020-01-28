package model.db.service;

import model.db.dao.AbstractDao;
import model.db.entity.Exhibition;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpositionDao extends AbstractDao<Exhibition.Exposition> {
    static final Logger logger = Logger.getLogger(MySqlExpositionDao.class);

    public MySqlExpositionDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO exposition VALUES (DEFAULT, ?, ?, ?, ?);";
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
            logger.error("Can't parse exposition result set", e);
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
            logger.error("Can't prepare exposition statement for insert", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Exhibition.Exposition object) {
        try {
            setData(statement, object);
            statement.setLong(5, object.getId());
        } catch (SQLException e) {
            logger.error("Can't prepare exposition statement for update", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Exhibition.Exposition object) {
        try {
            statement.setLong(1, object.getId());
        } catch (SQLException e) {
            logger.error("Can't prepare exposition statement for delete", e);
        }
    }
}
