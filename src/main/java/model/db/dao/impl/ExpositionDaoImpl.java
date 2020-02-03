package model.db.dao.impl;

import model.db.dao.AbstractDao;
import model.db.dao.Page;
import model.db.dao.PageableDao;
import model.db.entity.Exhibition;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utility.CollectionUtility.nullSafeListInitialize;

public class ExpositionDaoImpl extends AbstractDao<Exhibition.Exposition> implements PageableDao<Exhibition.Exposition> {
    private static final Logger logger = Logger.getLogger(ExpositionDaoImpl.class);

    public ExpositionDaoImpl(Connection connection) {
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
    protected String getFindByParamQuery() {
        return "SELECT * FROM exposition WHERE exhib_id = ?;";
    }

    private String getSelectPaginationQuery() {
        return "SELECT * FROM exposition WHERE exhib_id = ? LIMIT ? OFFSET ?;";
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
    protected void prepareData(PreparedStatement statement, Exhibition.Exposition object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getAbout());
        statement.setString(3, object.getImage());
        statement.setLong(4, object.getExhibId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Exhibition.Exposition object) {
        try {
            prepareData(statement, object);
        } catch (SQLException e) {
            logger.error("Can't prepare exposition statement for insert", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Exhibition.Exposition object) {
        try {
            prepareData(statement, object);
            statement.setLong(5, object.getId());
        } catch (SQLException e) {
            logger.error("Can't prepare exposition statement for update", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, long id) {
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            logger.error("Can't prepare exposition statement for delete", e);
        }
    }

    @Override
    public List<Exhibition.Exposition> findAll(long id, Page page, Connection connection) {
        List<Exhibition.Exposition> list = nullSafeListInitialize(null);
        String sql = getSelectPaginationQuery();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.setLong(2, page.getRecordNumber());
            ps.setLong(3, page.getPageNumber());
            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error in getting all in db", e);
        }
        return list;
    }
}
