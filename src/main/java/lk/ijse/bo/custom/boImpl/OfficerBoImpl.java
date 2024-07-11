package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Officer;
import lk.ijse.Model.OfficerDTO;
import lk.ijse.bo.custom.OfficerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.OfficerDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OfficerBoImpl implements OfficerBO {

    OfficerDAO officerDAO = (OfficerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.OFFICER);

    @Override
    public ArrayList<OfficerDTO> getAllOfficer() throws SQLException, ClassNotFoundException {

        ArrayList<Officer> allOfficer = officerDAO.getAll();
        ArrayList<OfficerDTO> allOfficers = new ArrayList<>();

        for (Officer officer : allOfficer) {
            allOfficers.add(new OfficerDTO(officer.getOfficerId(), officer.getOfficerFirstName(), officer.getOfficerLastName(), officer.getOfficerDOB(), officer.getOfficerNIC(), officer.getGender(), officer.getOfficerAddress(), officer.getOfficerEmail(), officer.getOfficerNumber(), officer.getPosition(), officer.getSectionId(),officer.getSalary()));
        }
        return allOfficers;
    }

    @Override
    public boolean saveOfficer(OfficerDTO officerDTO) throws SQLException, ClassNotFoundException {
        return officerDAO.save(new Officer(officerDTO.getOfficerId(), officerDTO.getOfficerFirstName(), officerDTO.getOfficerLastName(), officerDTO.getOfficerDOB(), officerDTO.getOfficerNIC(), officerDTO.getGender(), officerDTO.getOfficerAddress(), officerDTO.getOfficerEmail(), officerDTO.getOfficerNumber(), officerDTO.getPosition(), officerDTO.getSectionId(),officerDTO.getSalary()));
    }

    @Override
    public void updateOfficer(OfficerDTO officerDTO) throws SQLException, ClassNotFoundException {
        officerDAO.update(new Officer(officerDTO.getOfficerId(), officerDTO.getOfficerFirstName(), officerDTO.getOfficerLastName(), officerDTO.getOfficerDOB(), officerDTO.getOfficerNIC(), officerDTO.getGender(), officerDTO.getOfficerAddress(), officerDTO.getOfficerEmail(), officerDTO.getOfficerNumber(), officerDTO.getPosition(), officerDTO.getSectionId(),officerDTO.getSalary()));
    }

    @Override
    public void deleteOfficer(String id) throws SQLException, ClassNotFoundException {
        officerDAO.delete(id);
    }

    @Override
    public boolean existOfficer(String id) throws SQLException, ClassNotFoundException {
        return officerDAO.exist(id);
    }

    @Override
    public OfficerDTO searchOfficer(String searchOfficerId) throws SQLException, ClassNotFoundException {
        Officer officer = officerDAO.search(searchOfficerId);
        return new OfficerDTO(officer.getOfficerId(), officer.getOfficerFirstName(), officer.getOfficerLastName(), officer.getOfficerDOB(), officer.getOfficerNIC(), officer.getGender(), officer.getOfficerAddress(), officer.getOfficerEmail(), officer.getOfficerNumber(), officer.getPosition(), officer.getSectionId(),officer.getSalary());
    }
}
