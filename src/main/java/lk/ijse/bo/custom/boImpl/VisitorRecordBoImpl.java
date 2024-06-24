package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.VisitorRecord;
import lk.ijse.Model.VisitorRecordDTO;
import lk.ijse.bo.custom.VisitorRecordBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.VisitorRecordDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class VisitorRecordBoImpl implements VisitorRecordBO {

    VisitorRecordDAO visitorRecordDAO = (VisitorRecordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VISITOR_RECORD);

    @Override
    public ArrayList<VisitorRecordDTO> getAllVisitorRecord() throws SQLException, ClassNotFoundException {
        ArrayList<VisitorRecord> allVisitorRecord = visitorRecordDAO.getAll();
        ArrayList<VisitorRecordDTO> allVisitorRecords = new ArrayList<>();

        for (VisitorRecord visitorRecord : allVisitorRecord) {
            allVisitorRecords.add(new VisitorRecordDTO(visitorRecord.getVisitorRecordId(), visitorRecord.getVisitorId(), visitorRecord.getInmateId(), visitorRecord.getVisitDate(), visitorRecord.getVisitTime()));
        }
        return allVisitorRecords;
    }

    @Override
    public boolean saveVisitorRecord(VisitorRecordDTO visitorRecordDTO) throws SQLException, ClassNotFoundException {
        return visitorRecordDAO.save(new VisitorRecord(visitorRecordDTO.getVisitorRecordId(), visitorRecordDTO.getVisitorId(), visitorRecordDTO.getInmateId(), visitorRecordDTO.getVisitDate(), visitorRecordDTO.getVisitTime()));
    }

    @Override
    public void updateVisitorRecord(VisitorRecordDTO visitorRecordDTO) throws SQLException, ClassNotFoundException {
        visitorRecordDAO.update(new VisitorRecord(visitorRecordDTO.getVisitorRecordId(), visitorRecordDTO.getVisitorId(), visitorRecordDTO.getInmateId(), visitorRecordDTO.getVisitDate(), visitorRecordDTO.getVisitTime()));
    }

    @Override
    public void deleteVisitorRecord(String id) throws SQLException, ClassNotFoundException {
        visitorRecordDAO.delete(id);
    }

    @Override
    public boolean existVisitorRecord(String id) throws SQLException, ClassNotFoundException {
        return visitorRecordDAO.exist(id);
    }
}
