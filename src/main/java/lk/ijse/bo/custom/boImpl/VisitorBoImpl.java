package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Visitor;
import lk.ijse.Model.VisitorDTO;
import lk.ijse.bo.custom.VisitorBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.VisitorDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class VisitorBoImpl implements VisitorBO {

    VisitorDAO visitorDAO = (VisitorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VISITOR);

    @Override
    public ArrayList<VisitorDTO> getAllVisitor() throws SQLException, ClassNotFoundException {
        ArrayList<Visitor> allVisitor = visitorDAO.getAll();
        ArrayList<VisitorDTO> allVisitors = new ArrayList<>();

        for (Visitor visitor : allVisitor) {
            allVisitors.add(new VisitorDTO(visitor.getVisitorID(),visitor.getVisitorFirstName(),visitor.getVisitorLastName(),visitor.getVisitorDOB(),visitor.getVisitorNIC(),visitor.getVisitorNumber(),visitor.getVisitorAddress(),visitor.getVisitorType(),visitor.getGender(),visitor.getVisitorImage()));
        }
        return allVisitors;
    }

    @Override
    public boolean saveVisitor(VisitorDTO visitorDTO) throws SQLException, ClassNotFoundException {
        return visitorDAO.save(new Visitor(visitorDTO.getVisitorID(),visitorDTO.getVisitorFirstName(),visitorDTO.getVisitorLastName(),visitorDTO.getVisitorDOB(),visitorDTO.getVisitorNIC(),visitorDTO.getVisitorNumber(),visitorDTO.getVisitorAddress(),visitorDTO.getVisitorType(),visitorDTO.getGender(),visitorDTO.getVisitorImage()));
    }

    @Override
    public void updateVisitor(VisitorDTO visitorDTO) throws SQLException, ClassNotFoundException {
        visitorDAO.update(new Visitor(visitorDTO.getVisitorID(),visitorDTO.getVisitorFirstName(),visitorDTO.getVisitorLastName(),visitorDTO.getVisitorDOB(),visitorDTO.getVisitorNIC(),visitorDTO.getVisitorNumber(),visitorDTO.getVisitorAddress(),visitorDTO.getVisitorType(),visitorDTO.getGender(),visitorDTO.getVisitorImage()));
    }

    @Override
    public void deleteVisitor(String id) throws SQLException, ClassNotFoundException {
        visitorDAO.delete(id);
    }

    @Override
    public boolean existVisitor(String id) throws SQLException, ClassNotFoundException {
        return visitorDAO.exist(id);
    }
}
