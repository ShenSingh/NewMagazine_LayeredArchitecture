package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.VisitorRecord;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.VisitorRecordDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisitorRecordDAOImpl implements VisitorRecordDAO {
    @Override
    public ArrayList<VisitorRecord> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM VisitorRecord");

        ArrayList<VisitorRecord> getAllVisitorRecord = new ArrayList<>();

        while (rst.next()) {
            VisitorRecord entity = new VisitorRecord(
                    rst.getString("visitorRecordId"),
                    rst.getString("visitorId"),
                    rst.getString("inmateId"),
                    rst.getDate("visitDate"),
                    rst.getTime("visitTime")

            );
            getAllVisitorRecord.add(entity);
        }
        return getAllVisitorRecord;
    }

    @Override
    public boolean save(VisitorRecord dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("INSERT INTO VisitorRecord VALUES (?,?,?,?,?)",
                dto.getVisitorRecordId(),
                dto.getVisitorId(),
                dto.getInmateId(),
                dto.getVisitDate(),
                dto.getVisitTime()
        );
    }

    @Override
    public boolean update(VisitorRecord dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("UPDATE VisitorRecord SET visitorId=?, inmateId=?, visitDate=?, visitTime=? WHERE visitorRecordId=?",
                dto.getVisitorId(),
                dto.getInmateId(),
                dto.getVisitDate(),
                dto.getVisitTime(),
                dto.getVisitorRecordId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("DELETE FROM VisitorRecord WHERE visitorRecordId=?", id);
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
    public VisitorRecord search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM VisitorRecord WHERE visitorRecordId=?", id);
        if (rst.next()) {
            return new VisitorRecord(
                    rst.getString("visitorRecordId"),
                    rst.getString("visitorId"),
                    rst.getString("inmateId"),
                    rst.getDate("visitDate"),
                    rst.getTime("visitTime")
            );
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT visitorRecordId FROM VisitorRecord WHERE visitorRecordId=?", id);
        return rst.next();
    }
}
