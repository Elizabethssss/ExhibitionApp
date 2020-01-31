package model.db.dao.impl;

import model.db.dao.GenericDao;
import model.db.entity.Exhibition;
import model.db.entity.User;
import model.db.entity.UserExhib;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static final Logger logger = Logger.getLogger(DaoFactory.class);

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/aliens?useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "12345";

    static {
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
        return  connection;
    }

    public static GenericDao getDao(Connection connection, Class<?> clazz) {
        if(clazz == Exhibition.class)
            return new ExhibitionDao(connection);
        else if(clazz == User.class)
            return new UserDao(connection);
        else if(clazz == Exhibition.Exposition.class)
            return new ExpositionDao(connection);
        else if(clazz == UserExhib.class)
            return new UserExhibDao(connection);
        return null;
    }
}