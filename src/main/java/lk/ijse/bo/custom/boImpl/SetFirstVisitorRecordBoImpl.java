package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Visitor;
import lk.ijse.Entity.VisitorRecord;
import lk.ijse.Model.VisitorDTO;
import lk.ijse.Model.VisitorRecordDTO;
import lk.ijse.bo.custom.SetFirstVisitorRecordBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.VisitorDAO;
import lk.ijse.dao.custom.VisitorRecordDAO;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class SetFirstVisitorRecordBoImpl implements SetFirstVisitorRecordBO {

    VisitorDAO visitorDAO = (VisitorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VISITOR);
    VisitorRecordDAO visitorRecordDAO  = (VisitorRecordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VISITOR_RECORD);

    @Override
    public boolean setFirstVisitorRecord(VisitorDTO visitorDTO, VisitorRecordDTO visitorRecordDTO) throws SQLException {

        /*Transaction*/
        Connection connection = null;
        connection= DbConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isSaved = visitorDAO.save(new Visitor(visitorDTO.getVisitorID(), visitorDTO.getVisitorFirstName(), visitorDTO.getVisitorLastName(), visitorDTO.getVisitorDOB(), visitorDTO.getVisitorNIC(), visitorDTO.getVisitorNumber(), visitorDTO.getVisitorAddress(), visitorDTO.getVisitorType(), visitorDTO.getGender(), visitorDTO.getVisitorImage()));

            if (isSaved) {
                boolean isRecordSaved = visitorRecordDAO.save(new VisitorRecord(visitorRecordDTO.getVisitorRecordId(), visitorRecordDTO.getVisitorId(), visitorRecordDTO.getInmateId(), visitorRecordDTO.getVisitDate(), visitorRecordDTO.getVisitTime()));

                if (isRecordSaved) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }



    }
}
