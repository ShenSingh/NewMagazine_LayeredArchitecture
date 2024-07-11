package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Section;
import lk.ijse.Model.SectionDTO;
import lk.ijse.bo.custom.SectionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.SectionDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionBoImpl implements SectionBO {

    SectionDAO sectionDAO = (SectionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SECTION);

    @Override
    public ArrayList<SectionDTO> getAllSection() throws SQLException, ClassNotFoundException {
        ArrayList<Section> allSection = sectionDAO.getAll();
        ArrayList<SectionDTO> allSections = new ArrayList<>();

        for (Section section : allSection) {
            allSections.add(new SectionDTO(section.getSectionId(),section.getSectionName(),section.getLocation(),section.getCapacity(),section.getSecurityLevel(),section.getStatus()));
        }
        return allSections;
    }

    @Override
    public boolean saveSection(SectionDTO sectionDTO) throws SQLException, ClassNotFoundException {
        return sectionDAO.save(new Section(sectionDTO.getSectionId(),sectionDTO.getSectionName(),sectionDTO.getLocation(),sectionDTO.getCapacity(),sectionDTO.getSecurityLevel(),sectionDTO.getStatus()));
    }

    @Override
    public void updateSection(SectionDTO sectionDTO) throws SQLException, ClassNotFoundException {
        sectionDAO.update(new Section(sectionDTO.getSectionId(),sectionDTO.getSectionName(),sectionDTO.getLocation(),sectionDTO.getCapacity(),sectionDTO.getSecurityLevel(),sectionDTO.getStatus()));
    }

    @Override
    public void deleteSection(String id) throws SQLException, ClassNotFoundException {
        sectionDAO.delete(id);
    }

    @Override
    public boolean existSection(String id) throws SQLException, ClassNotFoundException {
        return sectionDAO.exist(id);
    }

    @Override
    public List<SectionDTO> getJailSections() throws SQLException, ClassNotFoundException {
        List<Section> jailSections = sectionDAO.getJailSections();
        List<SectionDTO> jailSectionsDTO = new ArrayList<>();

        for (Section section : jailSections) {
            jailSectionsDTO.add(new SectionDTO(section.getSectionId(),section.getSectionName(),section.getLocation(),section.getCapacity(),section.getSecurityLevel(),section.getStatus()));
        }
        return jailSectionsDTO;
    }

    @Override
    public SectionDTO searchSection(String Id) throws SQLException, ClassNotFoundException {
        Section section = sectionDAO.search(Id);
        return new SectionDTO(section.getSectionId(),section.getSectionName(),section.getLocation(),section.getCapacity(),section.getSecurityLevel(),section.getStatus());
    }

    @Override
    public List<SectionDTO> getSectionByHighSecurity() throws SQLException, ClassNotFoundException {
        ArrayList<SectionDTO> allSection =this.getAllSection();
        ArrayList<SectionDTO> highSecuritySections = new ArrayList<>();

        for (SectionDTO section : allSection) {
            if(section.getSecurityLevel().equals("High")){
                highSecuritySections.add(section);
            }
        }
        return highSecuritySections;
    }

    @Override
    public List<SectionDTO> getSectionByActive() throws SQLException, ClassNotFoundException {
        ArrayList<SectionDTO> allSection =this.getAllSection();
        ArrayList<SectionDTO> activeSections = new ArrayList<>();

        for (SectionDTO section : allSection) {
            if(section.getSecurityLevel().equals("Active")){
                activeSections.add(section);
            }
        }
        return activeSections;
    }
}
