package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.InmateRecord;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.InmateRecordDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InmateRecordDAOImpl implements InmateRecordDAO {
    @Override
    public ArrayList<InmateRecord> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM InmateRecord");

        ArrayList<InmateRecord> getAllInmateRecord = new ArrayList<>();
        while (rst.next()) {
            InmateRecord entity = new InmateRecord(
                    rst.getString("inmateId"),
                    rst.getString("sectionId"),
                    rst.getDate("entryDate"),
                    rst.getDate("releaseDate"),
                    rst.getString("crime"),
                    rst.getString("caseStatus")
            );
            getAllInmateRecord.add(entity);
        }
        return getAllInmateRecord;
    }

    @Override
    public boolean save(InmateRecord dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("INSERT INTO InmateRecord (inmateId, sectionId, entryDate, releaseDate, crime, caseStatus) VALUES (?,?,?,?,?,?)",
                dto.getInmateId(), dto.getSectionId(), dto.getEntryDate(), dto.getReleaseDate(), dto.getCrime(), dto.getCaseStatus());
    }

    @Override
    public boolean update(InmateRecord dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("UPDATE InmateRecord SET sectionId=?, entryDate=?, releaseDate=?, crime=?, caseStatus=? WHERE inmateId=?",
                dto.getSectionId(), dto.getEntryDate(), dto.getReleaseDate(), dto.getCrime(), dto.getCaseStatus(), dto.getInmateId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("DELETE FROM InmateRecord WHERE inmateId=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//        return null;
//    }

    @Override
    public InmateRecord search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM InmateRecord WHERE inmateId=?", id);
        resultSet.next();
        return new InmateRecord(id, resultSet.getString("sectionId"), resultSet.getDate("entryDate"), resultSet.getDate("releaseDate"), resultSet.getString("crime"), resultSet.getString("caseStatus"));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT inmateId FROM InmateRecord WHERE inmateId=?", id);
        return resultSet.next();
    }
}
