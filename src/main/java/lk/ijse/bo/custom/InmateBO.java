package lk.ijse.bo.custom;

import lk.ijse.Model.InmateDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InmateBO extends SuperBo{
    ArrayList<InmateDTO> getAllInmate() throws SQLException, ClassNotFoundException;

    boolean saveInmate(InmateDTO inmateDTO) throws SQLException, ClassNotFoundException;

    void updateInmate(InmateDTO inmateDTO) throws SQLException, ClassNotFoundException;

    void deleteInmate(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existInmate(String id) throws SQLException, ClassNotFoundException;
}
