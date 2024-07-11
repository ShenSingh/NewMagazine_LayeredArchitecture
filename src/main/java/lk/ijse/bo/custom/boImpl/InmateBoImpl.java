package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Inmate;
import lk.ijse.Model.InmateDTO;
import lk.ijse.bo.custom.InmateBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.InmateDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class InmateBoImpl implements InmateBO {
    InmateDAO inmateDAO = (InmateDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INMATE);
    @Override
    public ArrayList<InmateDTO> getAllInmate() throws SQLException, ClassNotFoundException {

        ArrayList<Inmate> allInmate = inmateDAO.getAll();
        ArrayList<InmateDTO> allInmates = new ArrayList<>();

        for (Inmate inmate : allInmate) {
            allInmates.add(new InmateDTO(inmate.getInmateId(),inmate.getInmateFirstName(),inmate.getInmateLastName(),inmate.getInmateDOB(),inmate.getInmateNIC(),inmate.getGender(),inmate.getInmateAddress(),inmate.getStatus(),inmate.getInmateImage()));
        }
        return allInmates;
    }

    @Override
    public boolean saveInmate(InmateDTO inmateDTO) throws SQLException, ClassNotFoundException {
        return inmateDAO.save(new Inmate(inmateDTO.getInmateId(),inmateDTO.getInmateFirstName(),inmateDTO.getInmateLastName(),inmateDTO.getInmateDOB(),inmateDTO.getInmateNIC(),inmateDTO.getGender(),inmateDTO.getInmateAddress(),inmateDTO.getStatus(),inmateDTO.getInmateImage()));
    }

    @Override
    public void updateInmate(InmateDTO inmateDTO) throws SQLException, ClassNotFoundException {
        inmateDAO.update(new Inmate(inmateDTO.getInmateId(),inmateDTO.getInmateFirstName(),inmateDTO.getInmateLastName(),inmateDTO.getInmateDOB(),inmateDTO.getInmateNIC(),inmateDTO.getGender(),inmateDTO.getInmateAddress(),inmateDTO.getStatus(),inmateDTO.getInmateImage()));
    }

    @Override
    public void deleteInmate(String id) throws SQLException, ClassNotFoundException {
        inmateDAO.delete(id);
    }

    @Override
    public boolean existInmate(String id) throws SQLException, ClassNotFoundException {
        return inmateDAO.exist(id);
    }

    @Override
    public InmateDTO searchInmate(String id) throws SQLException, ClassNotFoundException {
        Inmate inmate = inmateDAO.search(id);

        return new InmateDTO(inmate.getInmateId(), inmate.getInmateFirstName(), inmate.getInmateLastName(), inmate.getInmateDOB(), inmate.getInmateNIC(), inmate.getGender(), inmate.getInmateAddress(), inmate.getStatus(), inmate.getInmateImage());
    }

    @Override
    public ArrayList<InmateDTO> getInmatesByGender(String genderType) throws Exception {
        return inmateDAO.getInmatesByGender(genderType);
    }

    @Override
    public ArrayList<InmateDTO> getActiveInmates() throws SQLException, ClassNotFoundException {
        return inmateDAO.getActiveInmates();
    }
}
