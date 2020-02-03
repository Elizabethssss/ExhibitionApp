package controller;

import model.db.dao.GenericDao;
import model.db.entity.Exhibition;
import model.db.dao.impl.DaoFactory;

import java.sql.Connection;
import java.util.*;

public class DBExhibitionManager {
    private Connection con;
    private GenericDao<Exhibition> dao;
    private final String[] months = new String[] {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "November", "December"};

    public DBExhibitionManager() {
        this.con = DaoFactory.getConnection();
        this.dao = DaoFactory.getDao(con, Exhibition.class);
    }

    public Map<String, List<Exhibition>> getExhibitionsByMonth() {
        Map<String, List<Exhibition>> result = new LinkedHashMap<>();
        for (int i = 1; i <= months.length; i++) {
            List<Exhibition> temp = dao.findByParam("%-%" + i + "-%");
            result.put(months[i-1], temp);
        }
        return result;
    }

    public Optional<Exhibition> getExhibitionById(long id) {
        return dao.getById(id);
    }
}
