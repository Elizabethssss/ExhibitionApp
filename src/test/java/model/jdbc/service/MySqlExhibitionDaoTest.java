package model.jdbc.service;

import model.jdbc.dao.GenericDao;
import model.jdbc.entity.Exhibition;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MySqlExhibitionDaoTest {
    Exhibition temp;
    Connection con;
    GenericDao dao;

    @Before
    public void init() throws Exception {
        con = MySqlDaoFactory.getConnection();
        dao = MySqlDaoFactory.getDao(con, Exhibition.class);

        temp = new Exhibition();
        temp.setName("a");
        temp.setDate(new Date(System.currentTimeMillis()));
        temp.setTheme("a");
        temp.setAbout("a");
        temp.setPrice(10);
        temp.setImage("a");
    }

    @Test
    public void add() {
        assertTrue( dao.add(temp));
    }

    @Test
    public void update() {
        temp.setName("qwerty");
        temp.setId(3);
        assertTrue(dao.update(temp));
    }

    @Test
    public void getById() {
        Exhibition exhibition;
        temp.setId(2);
        exhibition = (Exhibition) dao.getById(temp.getId());
        assertNotNull(exhibition);
    }

    @Test
    public void getAll() throws Exception {
        List<Exhibition> list;
        list = dao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void delete() throws Exception {
        temp.setId(3);
        assertTrue(dao.delete(temp));
    }
}