package lk.ijse.bo.custom;

import lk.ijse.Model.ExpencesDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpencesBO extends SuperBo{
    ArrayList<ExpencesDTO> getAllExpences() throws SQLException, ClassNotFoundException;

    boolean saveExpences(ExpencesDTO expencesDTO) throws SQLException, ClassNotFoundException;

    void updateExpences(ExpencesDTO expencesDTO) throws SQLException, ClassNotFoundException;

    void deleteExpences(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existExpences(String id) throws SQLException, ClassNotFoundException;
}
