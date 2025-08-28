package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Visitor;
import lk.ijse.Model.VisitorDTO;
import lk.ijse.bo.custom.VisitorBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.VisitorDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitorBoImpl implements VisitorBO {

    VisitorDAO visitorDAO = (VisitorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VISITOR);

    @Override
    public ArrayList<VisitorDTO> getAllVisitor() throws SQLException, ClassNotFoundException {
        ArrayList<Visitor> allVisitor = visitorDAO.getAll();
        ArrayList<VisitorDTO> allVisitors = new ArrayList<>();

        for (Visitor visitor : allVisitor) {
            allVisitors.add(new VisitorDTO(visitor.getVisitorID(),visitor.getVisitorFirstName(),visitor.getVisitorLastName(),visitor.getVisitorDOB(),visitor.getVisitorNIC(),visitor.getVisitorNumber(),visitor.getVisitorAddress(),visitor.getVisitorType(),visitor.getGender()));
        }
        return allVisitors;
    }

    @Override
    public boolean saveVisitor(VisitorDTO visitorDTO) throws SQLException, ClassNotFoundException {
        return visitorDAO.save(new Visitor(visitorDTO.getVisitorID(),visitorDTO.getVisitorFirstName(),visitorDTO.getVisitorLastName(),visitorDTO.getVisitorDOB(),visitorDTO.getVisitorNIC(),visitorDTO.getVisitorNumber(),visitorDTO.getVisitorAddress(),visitorDTO.getVisitorType(),visitorDTO.getGender()));
    }

    @Override
    public void updateVisitor(VisitorDTO visitorDTO) throws SQLException, ClassNotFoundException {
        visitorDAO.update(new Visitor(visitorDTO.getVisitorID(),visitorDTO.getVisitorFirstName(),visitorDTO.getVisitorLastName(),visitorDTO.getVisitorDOB(),visitorDTO.getVisitorNIC(),visitorDTO.getVisitorNumber(),visitorDTO.getVisitorAddress(),visitorDTO.getVisitorType(),visitorDTO.getGender()));
    }

    @Override
    public void deleteVisitor(String id) throws SQLException, ClassNotFoundException {
        visitorDAO.delete(id);
    }

    @Override
    public boolean existVisitor(String id) throws SQLException, ClassNotFoundException {
        return visitorDAO.exist(id);
    }

    @Override
    public ArrayList<VisitorDTO> getVisitorByVisitorType(String immediateFamily) throws SQLException, ClassNotFoundException {

        ArrayList<VisitorDTO> visitorDTOS = this.getAllVisitor();
        ArrayList<VisitorDTO> immediateFamilyList = new ArrayList<>();

        for (VisitorDTO visitorDTO : visitorDTOS) {
            if (visitorDTO.getVisitorType().equals(immediateFamily)) {
                immediateFamilyList.add(visitorDTO);
            }
        }

        return immediateFamilyList;
    }

    @Override
    public VisitorDTO searchVisitor(String searchVisitorId) throws SQLException, ClassNotFoundException {
        Visitor visitor = visitorDAO.search(searchVisitorId);
        return new VisitorDTO(visitor.getVisitorID(),visitor.getVisitorFirstName(),visitor.getVisitorLastName(),visitor.getVisitorDOB(),visitor.getVisitorNIC(),visitor.getVisitorNumber(),visitor.getVisitorAddress(),visitor.getVisitorType(),visitor.getGender());
    }

    @Override
    public List<VisitorDTO> getVisitorsByInput(String input) throws SQLException, ClassNotFoundException {
        ArrayList<Visitor> visitors = visitorDAO.getVisitorsByInput(input);
        ArrayList<VisitorDTO> visitorDTOS = new ArrayList<>();

        for (Visitor visitor : visitors) {
            visitorDTOS.add(new VisitorDTO(visitor.getVisitorID(),visitor.getVisitorFirstName(),visitor.getVisitorLastName(),visitor.getVisitorDOB(),visitor.getVisitorNIC(),visitor.getVisitorNumber(),visitor.getVisitorAddress(),visitor.getVisitorType(),visitor.getGender()));
        }
        return visitorDTOS;
    }
}
