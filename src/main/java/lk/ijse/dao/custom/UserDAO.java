package lk.ijse.dao.custom;

import lk.ijse.Entity.User;
import lk.ijse.dao.CrudDvo;

public interface UserDAO extends CrudDvo<User> {
    boolean checkValid(String uId, String password) throws Exception;
}
