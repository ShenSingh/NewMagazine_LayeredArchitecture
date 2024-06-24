package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.InmateRecord;
import lk.ijse.Model.InmateRecordDTO;
import lk.ijse.bo.custom.InmateRecordBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.InmateRecordDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class InmateRecordBoImpl implements InmateRecordBO {

    InmateRecordDAO inmateRecordDAO = (InmateRecordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INMATE_RECORD);

    @Override
    public ArrayList<InmateRecordDTO> getAllInmateRecord() throws SQLException, ClassNotFoundException {
        ArrayList<InmateRecord> allInmateRecord = inmateRecordDAO.getAll();
        ArrayList<InmateRecordDTO> allInmateRecords = new ArrayList<>();

        for (InmateRecord inmateRecord : allInmateRecord) {
            allInmateRecords.add(new InmateRecordDTO(inmateRecord.getInmateId(),inmateRecord.getSectionId(),inmateRecord.getEntryDate(),inmateRecord.getReleaseDate(),inmateRecord.getCrime(),inmateRecord.getCaseStatus()));
        }
        return allInmateRecords;
    }

    @Override
    public boolean saveInmateRecord(InmateRecordDTO inmateRecordDTO) throws SQLException, ClassNotFoundException {
        return inmateRecordDAO.save(new InmateRecord(inmateRecordDTO.getInmateId(),inmateRecordDTO.getSectionId(),inmateRecordDTO.getEntryDate(),inmateRecordDTO.getReleaseDate(),inmateRecordDTO.getCrime(),inmateRecordDTO.getCaseStatus()));
    }

    @Override
    public void updateInmateRecord(InmateRecordDTO inmateRecordDTO) throws SQLException, ClassNotFoundException {
        inmateRecordDAO.update(new InmateRecord(inmateRecordDTO.getInmateId(),inmateRecordDTO.getSectionId(),inmateRecordDTO.getEntryDate(),inmateRecordDTO.getReleaseDate(),inmateRecordDTO.getCrime(),inmateRecordDTO.getCaseStatus()));
    }

    @Override
    public void deleteInmateRecord(String id) throws SQLException, ClassNotFoundException {
        inmateRecordDAO.delete(id);
    }

    @Override
    public boolean existInmateRecord(String id) throws SQLException, ClassNotFoundException {
        return inmateRecordDAO.exist(id);
    }
}
