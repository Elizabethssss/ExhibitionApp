package controller;

import model.db.dao.Page;
import model.db.dao.impl.DaoFactory;
import model.db.dao.impl.ExpositionDaoImpl;
import model.db.entity.Exhibition;

import java.sql.Connection;
import java.util.List;

public class DBExpositionManager {
    private Connection con;
    private ExpositionDaoImpl dao;

    public DBExpositionManager() {
        this.con = DaoFactory.getConnection();
        this.dao = (ExpositionDaoImpl) DaoFactory.getDao(con, Exhibition.Exposition.class);
    }

    public List<Exhibition.Exposition> getExpositionsByExhibId(long id, int page) {
        Page temp = new Page(page * 3, 3);
        return dao.findAll(id, temp, con);
    }

    public int countById(long id) {
        return dao.findByParam(id).size();
    }
}
