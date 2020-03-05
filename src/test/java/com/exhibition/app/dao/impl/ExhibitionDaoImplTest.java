package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.ExhibitionDao;
import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.entity.ExhibitionEntity;
import com.exhibition.app.exception.DataBaseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ExhibitionDaoImplTest {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";

    private HikariCPManager manager;
    private ExhibitionEntity exhibitionEntity;
    private ExhibitionEntity testExhibitionEntity;
    private ExhibitionDao exhibitionDao;

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
            exhibitionDao = new ExhibitionDaoImpl(manager);
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
    public void findByDateLikeShouldReturnList() {
        assertTrue(exhibitionDao.findByDateLike("%-%" + 6 + "-%").size() > 0);
    }

    @Test
    public void findByDateLikeShouldThrowException() {
        exception.expect(DataBaseException.class);
        assertEquals(0, exhibitionDao.findByDateLike("6").size());
    }

    @Test
    public void saveExhibitionShouldReturnTrue() {
        assertTrue(exhibitionDao.save(testExhibitionEntity));
    }

    @Test
    public void findByIdShouldReturnOptionalExhibitionEntity() {
        assertTrue(exhibitionDao.findById(exhibitionEntity.getId()).isPresent());
    }

    @Test
    public void findByIdShouldReturnOptionalEmpty() {
        assertFalse(exhibitionDao.findById(testExhibitionEntity.getId()).isPresent());
    }

    @Test
    public void updateShouldReturnTrue() {
        assertTrue(exhibitionDao.update(exhibitionEntity));
    }

    @Test
    public void updateShouldReturnFalse() {
        assertFalse(exhibitionDao.update(testExhibitionEntity));
    }

    @Test
    public void deleteShouldReturnFalse() {
        assertFalse(exhibitionDao.delete(0));
    }

    private void setEntities() {
        exhibitionEntity = ExhibitionEntity.builder()
                .withId(2L)
                .withName("Pinchuk Art Center")
                .withDateFrom(Date.valueOf("2020-06-06"))
                .withDateTo(Date.valueOf("2020-07-07"))
                .withTheme("Art")
                .withAbout("Modern art")
                .withLongAbout("Pictures, sculptures, movies and more")
                .withPrice(200)
                .withImage("/img/101.png")
                .build();
        testExhibitionEntity = ExhibitionEntity.builder()
                .withId(5L)
                .withName("Forrest gump")
                .withDateFrom(Date.valueOf("2020-01-01"))
                .withDateTo(Date.valueOf("2020-01-02"))
                .withTheme("Nature")
                .withAbout("Real forest")
                .withLongAbout("You can see real deers")
                .withPrice(2020.20)
                .withImage("/img/20.png")
                .build();
    }
}