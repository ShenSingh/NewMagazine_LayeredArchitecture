package lk.ijse.bo.custom;

import lk.ijse.Model.VisitorRecordDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VisitorRecordBO extends SuperBo{
    ArrayList<VisitorRecordDTO> getAllVisitorRecord() throws SQLException, ClassNotFoundException;

    boolean saveVisitorRecord(VisitorRecordDTO visitorRecordDTO) throws SQLException, ClassNotFoundException;

    void updateVisitorRecord(VisitorRecordDTO visitorRecordDTO) throws SQLException, ClassNotFoundException;

    void deleteVisitorRecord(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existVisitorRecord(String id) throws SQLException, ClassNotFoundException;
}
