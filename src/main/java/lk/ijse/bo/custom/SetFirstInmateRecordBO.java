package lk.ijse.bo.custom;

import lk.ijse.Model.InmateDTO;
import lk.ijse.Model.InmateRecordDTO;

import java.sql.Connection;

public interface SetFirstInmateRecordBO {
    boolean setFirstInmateRecord(InmateDTO inmateDTO, InmateRecordDTO inmateRecordDTO) throws Exception;


}
