package controller;

import model.db.dao.UserDao;
import model.db.dao.impl.DaoFactory;
import model.db.entity.User;

import java.sql.Connection;
import java.util.List;

public class DBUserManager {
    private Connection con;
    private UserDao dao;

    public DBUserManager() {
        this.con = DaoFactory.getConnection();
        this.dao = (UserDao) DaoFactory.getDao(con, User.class);
    }

    public boolean addUserToDB(String username, String email, String password) {
        User temp = new User();
        temp.setUsername(username);
        temp.setEmail(email);
        temp.setPassword(password);
        return dao.add(temp);
    }

    public boolean checkUsername(String username) {
        return dao.checkUsername(username, con);
    }

    public boolean checkEmail(String email) {
        return dao.checkEmail(email, con);
    }

    public boolean checkPassword(String password) {
        return dao.checkPassword(password, con);
    }

    public boolean findUser(String username, String password) {
        return checkUsername(username) && checkPassword(password);
    }

    public long getIdByUsername(String sessionName) {
        List<User> users = dao.findByParam(sessionName);
        return users.get(0).getId();
    }
}
