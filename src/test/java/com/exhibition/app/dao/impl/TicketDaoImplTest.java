package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.Page;
import com.exhibition.app.dao.TicketDao;
import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.entity.TicketEntity;
import com.exhibition.app.exception.DataBaseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class TicketDaoImplTest {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";

    private HikariCPManager manager;
    private TicketEntity ticketEntity;
    private TicketEntity ticketEntity2;
    private TicketEntity testTicketEntity;
    private TicketDao ticketDao;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        try {
            manager = new HikariCPManager(DB_PROP_PATH);
            Connection connection = manager.getConnection();
            Statement statement = connection.createStatement();
            String schemaQuery = new String(Files.readAllBytes(Paths.get(SCHEMA_PATH)));
            statement.execute(schemaQuery);
            String dataQuery = new String(Files.readAllBytes(Paths.get(DATA_PATH)));
            statement.execute(dataQuery);
            statement.close();
            connection.close();
            ticketDao = new TicketDaoImpl(manager);
            setEntities();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void shutDown() {
        manager.shutdown();
    }

    @Test
    public void findByUserIdAndIsBoughtShouldReturnList() {
        assertTrue(ticketDao.findByUserIdAndIsBought(ticketEntity.getUserId(), ticketEntity.isBought()).size() > 0);
    }

//    @Test
//    public void findByUserIdAndIsBoughtShouldThrowException() {
//        exception.expect(DataBaseException.class);
//        assertFalse(ticketDao.findByUserIdAndIsBought(testTicketEntity.getUserId(),
//                testTicketEntity.isBought()).size() > 0);
//    }

   @Test
    public void findAllShouldReturnPageList() {
        assertTrue(ticketDao.findAll(ticketEntity.getUserId(),
                new Page(0, 1)).size() > 0);
    }

    @Test
    public void countShouldReturnValueGraterThen0() {
        assertTrue(ticketDao.count(ticketEntity.getUserId()) > 0);
    }

    @Test
    public void saveTicketShouldReturnTrue() {
        assertTrue(ticketDao.save(ticketEntity));
    }

    @Test
    public void findByIdShouldReturnOptionalTicketEntity() {
        assertTrue(ticketDao.findById(ticketEntity.getId()).isPresent());
    }

    @Test
    public void findByIdShouldReturnOptionalEmpty() {
        assertFalse(ticketDao.findById(testTicketEntity.getId()).isPresent());
    }

    @Test
    public void updateShouldReturnTrue() {
        assertTrue(ticketDao.update(ticketEntity));
    }

    @Test
    public void updateShouldReturnFalse() {
        assertFalse(ticketDao.update(testTicketEntity));
    }

    @Test
    public void deleteShouldReturnFalse() {
        assertFalse(ticketDao.delete(0));
    }

    private void setEntities() {
        ticketEntity = TicketEntity.builder()
                .withId(1L)
                .withUserId(3L)
                .withExhibitionId(3L)
                .withIsBought(false)
                .build();
        ticketEntity2 = TicketEntity.builder()
                .withId(1L)
                .withUserId(3L)
                .withExhibitionId(3L)
                .withIsBought(true)
                .build();
        testTicketEntity = TicketEntity.builder()
                .withId(5L)
                .withUserId(5L)
                .withExhibitionId(5L)
                .withIsBought(true)
                .build();
    }
}