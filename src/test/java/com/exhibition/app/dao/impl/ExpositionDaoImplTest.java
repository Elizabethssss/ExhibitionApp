package com.exhibition.app.dao.impl;

import com.exhibition.app.dao.ExpositionDao;
import com.exhibition.app.dao.Page;
import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.entity.ExpositionEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExpositionDaoImplTest {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";

    private HikariCPManager manager;
    private ExpositionEntity expositionEntity;
    private ExpositionEntity testExpositionEntity;
    private ExpositionDao expositionDao;

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
            expositionDao = new ExpositionDaoImpl(manager);
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
    public void findByExhibitionIdShouldReturnOptionalExpositionEntity() {
        assertTrue(expositionDao.findByExhibitionId(expositionEntity.getExhibitionId()).isPresent());
    }

    @Test
    public void findByExhibitionIdShouldReturnOptionalEmpty() {
        assertFalse(expositionDao.findByExhibitionId(testExpositionEntity.getExhibitionId()).isPresent());
    }

    @Test
    public void findAllShouldReturnPageList() {
        assertTrue(expositionDao.findAll(expositionEntity.getId(),
                new Page(1, 1)).size() > 0);
    }

    @Test
    public void countShouldReturnValueGraterThen0() {
        assertTrue(expositionDao.count(expositionEntity.getId()) > 0);
    }

    @Test
    public void findByIdShouldReturnOptionalExpositionEntity() {
        assertTrue(expositionDao.findById(expositionEntity.getId()).isPresent());
    }

    @Test
    public void saveExpositionShouldReturnTrue() {
        assertTrue(expositionDao.save(expositionEntity));
    }

    @Test
    public void updateShouldReturnTrue() {
        assertTrue(expositionDao.update(expositionEntity));
    }

    @Test
    public void updateShouldReturnFalse() {
        assertFalse(expositionDao.update(testExpositionEntity));
    }

    @Test
    public void deleteShouldReturnTrue() {
        assertTrue(expositionDao.delete(expositionEntity.getId()));
    }

    private void setEntities() {
        expositionEntity = ExpositionEntity.builder()
                .withId(1L)
                .withName("Scream")
                .withAbout("Legendary art")
                .withImage("/img/1.jpg")
                .withExhibitionId(1L)
                .build();
        testExpositionEntity = ExpositionEntity.builder()
                .withId(5L)
                .withName("Deer")
                .withAbout("Forest deer")
                .withImage("/deer.jpg")
                .withExhibitionId(5L)
                .build();
    }
}