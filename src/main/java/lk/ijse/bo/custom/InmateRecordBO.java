package lk.ijse.bo.custom;

import lk.ijse.Model.InmateRecordDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InmateRecordBO extends SuperBo{
    ArrayList<InmateRecordDTO> getAllInmateRecord() throws SQLException, ClassNotFoundException;

    boolean saveInmateRecord(InmateRecordDTO inmateRecordDTO) throws SQLException, ClassNotFoundException;

    void updateInmateRecord(InmateRecordDTO inmateRecordDTO) throws SQLException, ClassNotFoundException;

    void deleteInmateRecord(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existInmateRecord(String id) throws SQLException, ClassNotFoundException;
}
