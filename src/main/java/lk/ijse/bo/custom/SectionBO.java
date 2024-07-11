package lk.ijse.bo.custom;

import lk.ijse.Entity.Section;
import lk.ijse.Model.OfficerDTO;
import lk.ijse.Model.SectionDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SectionBO extends SuperBo{
    ArrayList<SectionDTO> getAllSection() throws SQLException, ClassNotFoundException;

    boolean saveSection(SectionDTO sectionDTO) throws SQLException, ClassNotFoundException;

    void updateSection(SectionDTO sectionDTO) throws SQLException, ClassNotFoundException;

    void deleteSection(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existSection(String id) throws SQLException, ClassNotFoundException;

    List<SectionDTO> getJailSections() throws SQLException, ClassNotFoundException;

    SectionDTO searchSection(String Id) throws SQLException, ClassNotFoundException;

    List<SectionDTO> getSectionByHighSecurity() throws SQLException, ClassNotFoundException;

    List<SectionDTO> getSectionByActive() throws SQLException, ClassNotFoundException;
}
