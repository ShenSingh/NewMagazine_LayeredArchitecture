package lk.ijse.bo.custom;

import lk.ijse.Model.OfficerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OfficerBO extends SuperBo{
    ArrayList<OfficerDTO> getAllOfficer() throws SQLException, ClassNotFoundException;

    boolean saveOfficer(OfficerDTO officerDTO) throws SQLException, ClassNotFoundException;

    void updateOfficer(OfficerDTO officerDTO) throws SQLException, ClassNotFoundException;

    void deleteOfficer(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existOfficer(String id) throws SQLException, ClassNotFoundException;

    OfficerDTO searchOfficer(String searchOfficerId) throws SQLException, ClassNotFoundException;
}
