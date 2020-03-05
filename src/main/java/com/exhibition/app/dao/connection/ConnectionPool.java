package com.exhibition.app.dao.connection;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
    void shutdown();
}
