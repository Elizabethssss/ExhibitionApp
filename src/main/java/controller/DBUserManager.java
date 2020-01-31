package controller;

import model.db.dao.GenericDao;
import model.db.entity.User;
import model.db.dao.impl.DaoFactory;

import java.sql.Connection;
import java.util.List;

public class DBUserManager {
    Connection con;
    GenericDao dao;

    public DBUserManager() {
        this.con = DaoFactory.getConnection();
        this.dao = DaoFactory.getDao(con, User.class);
    }

    public boolean addUserToDB(String username, String email, String password) {
        User temp = new User();
        temp.setUsername(username);
        temp.setEmail(email);
        temp.setPassword(password);
        if(dao.add(temp))
            return true;
        return false;
    }

    public boolean checkUsername(String username) {
        List<User> users = dao.getAll();
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmail(String email) {
        List<User> users = dao.getAll();
        for(User user : users) {
            if(user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public boolean checkPassword(String password) {
        List<User> users = dao.getAll();
        for(User user : users) {
            if(user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    public boolean findUser(String username, String password) {
        return checkUsername(username) && checkPassword(password);
    }

    public long getIdByUsername(String sessionName) {
        long id = 0;
        List<User> users = dao.getAll();
        for (User user : users) {
            if (user.getUsername().equals(sessionName)) {
                id = user.getId();
            }
        }
        return id;
    }
}
