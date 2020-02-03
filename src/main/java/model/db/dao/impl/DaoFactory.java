package model.db.dao.impl;

import model.db.dao.AbstractDao;
import model.db.dao.GenericDao;
import model.db.entity.Exhibition;
import model.db.entity.User;
import model.db.entity.UserExhib;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DaoFactory {
    private static final Logger logger = Logger.getLogger(DaoFactory.class);
    private static ResourceBundle resources;

    public static final String FILENAME = "db";
    public static final String DRIVER;
    public static final String URL;
    public static final String USER;
    public static final String PASSWORD;

    static {
        resources = ResourceBundle.getBundle(FILENAME);
        DRIVER = resources.getString("driver");
        URL = resources.getString("url");
        USER = resources.getString("user");
        PASSWORD = resources.getString("password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("Can't get driver", e);
        }
    }

    private DaoFactory() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error("Can't connect to db", e);
        }
        return connection;
    }

    public static <T> AbstractDao<? extends Object> getDao(Connection connection, Class<?> clazz) {
        if (clazz == Exhibition.class)
            return new ExhibitionDaoImpl(connection);
        else if (clazz == User.class)
            return new UserDaoImpl(connection);
        else if (clazz == Exhibition.Exposition.class)
            return new ExpositionDaoImpl(connection);
        else if (clazz == UserExhib.class)
            return new UserExhibDaoImpl(connection);
        return null;
    }
}
