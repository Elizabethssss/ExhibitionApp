package model.db.service;

import model.db.dao.GenericDao;
import model.db.entity.Exhibition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MySqlExhibitionDaoTest {
    Exhibition temp;
    Connection con;
    GenericDao dao;

    @Parameterized.Parameter
    public Class<?> clazz;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { Exhibition.class }
        });
    }

    @Before
    public void init() throws Exception {
        con = MySqlDaoFactory.getConnection();
        dao = MySqlDaoFactory.getDao(con, clazz);

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
        Optional<Exhibition> exhibition;
        temp.setId(2);
        exhibition = dao.getById(temp.getId());
        assertNotNull(exhibition);
    }

    @Test
    public void getAll() throws Exception {
        List list = dao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void delete() throws Exception {
        temp.setId(3);
        assertTrue(dao.delete(temp));
    }
}