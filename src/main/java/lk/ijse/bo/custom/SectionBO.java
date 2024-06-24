package lk.ijse.bo.custom;

import lk.ijse.Model.SectionDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SectionBO extends SuperBo{
    ArrayList<SectionDTO> getAllSection() throws SQLException, ClassNotFoundException;

    boolean saveSection(SectionDTO sectionDTO) throws SQLException, ClassNotFoundException;

    void updateSection(SectionDTO sectionDTO) throws SQLException, ClassNotFoundException;

    void deleteSection(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existSection(String id) throws SQLException, ClassNotFoundException;
}
