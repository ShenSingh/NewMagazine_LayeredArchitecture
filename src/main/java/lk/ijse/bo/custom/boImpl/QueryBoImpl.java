package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Inmate;
import lk.ijse.Model.InmateDTO;
import lk.ijse.bo.custom.QuaryBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.QueryDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBoImpl implements QuaryBo{

    QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ArrayList<InmateDTO> getActiveCaseInmate() throws SQLException, ClassNotFoundException {
        ArrayList<Inmate> activeCaseInmate = queryDao.getActiveCaseInmate();
        ArrayList<InmateDTO> activeCaseInmates = new ArrayList<>();

        for (Inmate inmate : activeCaseInmate) {
            activeCaseInmates.add(new InmateDTO(inmate.getInmateId(), inmate.getInmateFirstName(), inmate.getInmateLastName(), inmate.getInmateDOB(), inmate.getInmateNIC(), inmate.getGender(), inmate.getInmateAddress(), inmate.getStatus()));
        }
        return activeCaseInmates;
    }

    @Override
    public ArrayList<InmateDTO> getReleaseSoonInmates() throws SQLException, ClassNotFoundException {
        ArrayList<Inmate> releaseSoonInmates = queryDao.getReleaseSoonInmates();
        ArrayList<InmateDTO> releaseSoonInmate = new ArrayList<>();

        for (Inmate inmate : releaseSoonInmates) {
            releaseSoonInmate.add(new InmateDTO(inmate.getInmateId(), inmate.getInmateFirstName(), inmate.getInmateLastName(), inmate.getInmateDOB(), inmate.getInmateNIC(), inmate.getGender(), inmate.getInmateAddress(), inmate.getStatus()));
        }
        return releaseSoonInmate;
    }
}
