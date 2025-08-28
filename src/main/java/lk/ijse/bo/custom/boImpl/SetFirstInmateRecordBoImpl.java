package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.Inmate;
import lk.ijse.Entity.InmateRecord;
import lk.ijse.Model.InmateDTO;
import lk.ijse.Model.InmateRecordDTO;
import lk.ijse.bo.custom.SetFirstInmateRecordBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.InmateDAO;
import lk.ijse.dao.custom.InmateRecordDAO;
import lk.ijse.db.DbConnection;

import java.sql.Connection;

public class SetFirstInmateRecordBoImpl implements SetFirstInmateRecordBO {

    InmateDAO inmateDAO = (InmateDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INMATE);
    InmateRecordDAO inmateRecordDAO = (InmateRecordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INMATE_RECORD);

    @Override
    public boolean setFirstInmateRecord(InmateDTO inmateDTO, InmateRecordDTO inmateRecordDTO) throws Exception {

        /*Transaction*/
        Connection connection = null;
        connection= DbConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isSaved = inmateDAO.save(new Inmate(inmateDTO.getInmateId(), inmateDTO.getInmateFirstName(), inmateDTO.getInmateLastName(), inmateDTO.getInmateDOB(), inmateDTO.getInmateNIC(), inmateDTO.getGender(), inmateDTO.getInmateAddress(), inmateDTO.getStatus()));

            if (isSaved) {
                boolean isRecordSaved = inmateRecordDAO.save(new InmateRecord(inmateRecordDTO.getInmateId(),inmateRecordDTO.getSectionId(), inmateRecordDTO.getEntryDate(), inmateRecordDTO.getReleaseDate(), inmateRecordDTO.getCrime(), inmateRecordDTO.getCaseStatus()));

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
        } catch (Exception e) {
            connection.rollback();
            System.out.println("e"+e);
            throw new RuntimeException(e);
        }

        finally {
            connection.setAutoCommit(true);
        }

    }
}
