package lk.ijse.bo.custom;

import lk.ijse.Entity.Inmate;
import lk.ijse.Model.InmateDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QuaryBo {
    ArrayList<InmateDTO> getActiveCaseInmate() throws SQLException, ClassNotFoundException;
    ArrayList<InmateDTO> getReleaseSoonInmates() throws SQLException, ClassNotFoundException;
}
