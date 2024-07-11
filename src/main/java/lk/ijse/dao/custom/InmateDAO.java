package lk.ijse.dao.custom;

import lk.ijse.Entity.Inmate;
import lk.ijse.Model.InmateDTO;
import lk.ijse.dao.CrudDvo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InmateDAO extends CrudDvo<Inmate> {
    ArrayList<InmateDTO> getInmatesByGender(String genderType) throws Exception;

    ArrayList<InmateDTO> getActiveInmates() throws SQLException, ClassNotFoundException;

}
