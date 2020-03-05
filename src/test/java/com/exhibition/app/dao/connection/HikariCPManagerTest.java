package com.exhibition.app.dao.connection;

import com.exhibition.app.exception.DataBaseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class HikariCPManagerTest {
    private static final String ACTUAL_DB_PROPERTIES_FILE = "db";
    private static final String TEST_DB_PROPERTIES_FILE = "h2_test_db";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testConfig() {
        HikariCPManager manager = new HikariCPManager(ACTUAL_DB_PROPERTIES_FILE);
        assertNotNull(manager);
        assertNotNull(manager.getConnection());
        manager.shutdown();
    }

    @Test
    public void testH2Config() {
        HikariCPManager manager = new HikariCPManager(TEST_DB_PROPERTIES_FILE);
        assertNotNull(manager);
        assertNotNull(manager.getConnection());
        manager.shutdown();
    }

    @Test
    public void testConnectionShouldThrowDataBaseException() {
        expectedException.expect(DataBaseException.class);
        HikariCPManager manager = new HikariCPManager(ACTUAL_DB_PROPERTIES_FILE);
        manager.shutdown();
        manager.getConnection();
    }

}