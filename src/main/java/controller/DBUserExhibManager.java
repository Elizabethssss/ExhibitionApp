package controller;

import model.db.dao.Page;
import model.db.dao.impl.DaoFactory;
import model.db.dao.impl.UserExhibDaoImpl;
import model.db.entity.UserExhib;

import java.sql.Connection;
import java.util.List;

public class DBUserExhibManager {
    private Connection con;
    private UserExhibDaoImpl dao;

    public DBUserExhibManager() {
        this.con = DaoFactory.getConnection();
        this.dao = (UserExhibDaoImpl) DaoFactory.getDao(con, UserExhib.class);
    }

    public void add(long userId, long exhibId) {
        UserExhib temp = new UserExhib();
        temp.setUserId(userId);
        temp.setExhibitionId(exhibId);
        temp.setBought(false);
        dao.add(temp);
    }

    public List<UserExhib> getUserExhibsByUserId(long userId, boolean isBought) {
        return dao.getExhibsByUserIdAndIsBought(userId, isBought, con);
    }

    public List<UserExhib> getPaginationUserExhibs(long userId, int page) {
        Page temp = new Page(page * 5, 5);
        return dao.findAll(userId, temp, con);
    }

    public void deleteById(long ticketId) {
        dao.delete(ticketId);
    }

    public void updateIsBought(long id) {
        UserExhib temp = new UserExhib();
        temp.setUserId(id);
        temp.setBought(true);
        dao.update(temp);
    }
}
