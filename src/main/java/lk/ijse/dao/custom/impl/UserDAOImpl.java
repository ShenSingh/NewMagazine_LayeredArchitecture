package lk.ijse.dao.custom.impl;

import lk.ijse.Controller.Util.jbcrypt.PasswordHasher;
import lk.ijse.Entity.User;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    PasswordHasher passwordHasher = new PasswordHasher();

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM User");

        ArrayList<User> getAllUser = new ArrayList<>();
        while (rst.next()) {
            User entity = new User(
                    rst.getString("uId"),
                    rst.getString("uName"),
                    rst.getString("uEmail"),
                    rst.getString("uPassword"),
                    rst.getString("addressLine1"),
                    rst.getString("addressLine2"),
                    rst.getString("phone"),
                    rst.getString("gender"),
                    rst.getString("DOB"),
                    rst.getBytes("imageData")
                    );
            getAllUser.add(entity);
        }
        return getAllUser;
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?,?)",
                dto.getUId(),
                dto.getUName(),
                dto.getUEmail(),
                dto.getUPassword(),
                dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getPhone(),
                dto.getGender(),
                dto.getDob(),
                dto.getImageData());
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("UPDATE User SET uName=?, uEmail=?, uPassword=?, addressLine1=?, addressLine2=?, phone=?, gender=?, DOB=?, imageData=? WHERE uId=?",
                dto.getUName(),
                dto.getUEmail(),
                dto.getUPassword(),
                dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getPhone(),
                dto.getGender(),
                dto.getDob(),
                dto.getImageData(),
                dto.getUId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("DELETE FROM User WHERE uId=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        return null;
    }

//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//        return null;
//    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM User WHERE uId=?", id);
        rst.next();
        return new User(
                id,
                rst.getString("uName"),
                rst.getString("uEmail"),
                rst.getString("uPassword"),
                rst.getString("addressLine1"),
                rst.getString("addressLine2"),
                rst.getString("phone"),
                rst.getString("gender"),
                rst.getString("DOB"),
                rst.getBytes("imageData")
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT uId FROM User WHERE uId=?", id);
        return rst.next();
    }

    @Override
    public boolean checkValid(String uId, String password) throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT uPassword FROM User WHERE uId=?", uId);

        if (rst.next()) {
            return passwordHasher.checkPassword(password, rst.getString(1));
        }
        return false;
    }
}
