package lk.ijse.bo.custom;

import lk.ijse.Model.InmateDTO;
import lk.ijse.Model.VisitorDTO;
import lk.ijse.Model.VisitorRecordDTO;

import java.sql.SQLException;

public interface SetFirstVisitorRecordBO {
    boolean setFirstVisitorRecord(VisitorDTO visitorDTO, VisitorRecordDTO visitorRecordDTO) throws SQLException;
}
