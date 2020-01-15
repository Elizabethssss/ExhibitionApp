package model.jdbc.service;

import model.jdbc.dao.GenericDao;
import model.jdbc.entity.Exhibition;
import model.jdbc.entity.User;
import model.jdbc.entity.UserExhib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MySqlDaoFactory {
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/aliens?useSSL=false";
    public static final String user = "root";
    public static final String password = "12345";

    MySqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

    public static GenericDao getDao(Connection connection, Class<?> clazz) {
        if(clazz == Exhibition.class)
            return new MySqlExhibitionDao(connection);
        else if(clazz == User.class)
            return new MySqlUserDao(connection);
        else if(clazz == Exhibition.Exposition.class)
            return new MySqlExpositionDao(connection);
        else if(clazz == UserExhib.class)
            return new MySqlUserExhibDao(connection);
        return null;
    }
}
