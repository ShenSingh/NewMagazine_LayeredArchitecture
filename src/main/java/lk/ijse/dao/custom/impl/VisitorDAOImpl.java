package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Visitor;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.VisitorDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisitorDAOImpl implements VisitorDAO {
    @Override
    public ArrayList<Visitor> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Visitor");

        ArrayList<Visitor> allVisitors = new ArrayList<>();

        while (rst.next()) {
            Visitor visitor = new Visitor(
                    rst.getString("visitorId"),
                    rst.getString("visitorFirstName"),
                    rst.getString("visitorLastName"),
                    rst.getDate("visitorDOB"),
                    rst.getString("visitorNIC"),
                    rst.getString("gender"),
                    rst.getInt("visitorNumber"),
                    rst.getString("visitorAddress"),
                    rst.getString("visitorType"),
                    rst.getBytes("imageData")

                    );
            allVisitors.add(visitor);
        }
        return allVisitors;
    }

    @Override
    public boolean save(Visitor dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("INSERT INTO Visitor VALUES (?,?,?,?,?,?,?,?,?,?,?)",
                dto.getVisitorID(),
                dto.getVisitorFirstName(),
                dto.getVisitorLastName(),
                dto.getVisitorDOB(),
                dto.getVisitorNIC(),
                dto.getGender(),
                dto.getVisitorNumber(),
                dto.getVisitorAddress(),
                dto.getVisitorType(),
                dto.getVisitorImage()
        );
    }

    @Override
    public boolean update(Visitor dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("UPDATE Visitor SET visitorFirstName=?, visitorLastName=?, visitorDOB=?, visitorNIC=?, gender=?, visitorNumber=?, visitorAddress=?, visitorType=?, visitorImage=? WHERE visitorID=?",
                dto.getVisitorFirstName(),
                dto.getVisitorLastName(),
                dto.getVisitorDOB(),
                dto.getVisitorNIC(),
                dto.getGender(),
                dto.getVisitorNumber(),
                dto.getVisitorAddress(),
                dto.getVisitorType(),
                dto.getVisitorImage(),
                dto.getVisitorID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("DELETE FROM Visitor WHERE visitorID=?", id);
    }

//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//        return null;
//    }

    @Override
    public Visitor search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Visitor WHERE visitorID=?", id);
        if (rst.next()) {
            return new Visitor(
                    rst.getString("visitorID"),
                    rst.getString("visitorFirstName"),
                    rst.getString("visitorLastName"),
                    rst.getDate("visitorDOB"),
                    rst.getString("visitorNIC"),
                    rst.getString("gender"),
                    rst.getInt("visitorNumber"),
                    rst.getString("visitorAddress"),
                    rst.getString("visitorType"),
                    rst.getBytes("visitorImage")
            );
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT visitorID FROM Visitor WHERE visitorID=?", id);
        return rst.next();
    }

}
