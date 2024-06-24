package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Expences;
import lk.ijse.Model.ExpencesDTO;
import lk.ijse.bo.custom.ExpencesBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ExpencesDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpencesBoImpl implements ExpencesBO {

    ExpencesDAO expencesDAO = (ExpencesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXPENCES);

    @Override
    public ArrayList<ExpencesDTO> getAllExpences() throws SQLException, ClassNotFoundException {
        ArrayList<Expences> allExpence = expencesDAO.getAll();
        ArrayList<ExpencesDTO> allExpences = new ArrayList<>();

        for (Expences expences : allExpence) {
            allExpences.add(new ExpencesDTO(expences.getExpencesID(),expences.getSectionId(),expences.getMonth(),expences.getType(),expences.getCost()));
        }
        return allExpences;
    }

    @Override
    public boolean saveExpences(ExpencesDTO expencesDTO) throws SQLException, ClassNotFoundException {
        return expencesDAO.save(new Expences(expencesDTO.getExpencesID(),expencesDTO.getSectionId(),expencesDTO.getMonth(),expencesDTO.getType(),expencesDTO.getCost()));
    }

    @Override
    public void updateExpences(ExpencesDTO expencesDTO) throws SQLException, ClassNotFoundException {
        expencesDAO.update(new Expences(expencesDTO.getExpencesID(),expencesDTO.getSectionId(),expencesDTO.getMonth(),expencesDTO.getType(),expencesDTO.getCost()));
    }

    @Override
    public void deleteExpences(String id) throws SQLException, ClassNotFoundException {
        expencesDAO.delete(id);
    }

    @Override
    public boolean existExpences(String id) throws SQLException, ClassNotFoundException {
        return expencesDAO.exist(id);
    }
}
