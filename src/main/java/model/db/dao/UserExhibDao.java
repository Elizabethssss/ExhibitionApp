package model.db.dao;

import model.db.entity.UserExhib;

import java.sql.Connection;
import java.util.List;

public interface UserExhibDao extends GenericDao<UserExhib> {
    List<UserExhib> getExhibsByUserIdAndIsBought(long userId, boolean isBought, Connection connection);
}
