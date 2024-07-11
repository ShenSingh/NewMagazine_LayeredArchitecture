package lk.ijse.bo.custom;

import lk.ijse.Model.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBo{
    ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException;

    boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    void updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    void deleteUser(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existUser(String id) throws SQLException, ClassNotFoundException;

    boolean checkValid(String uId, String password) throws Exception;

}
