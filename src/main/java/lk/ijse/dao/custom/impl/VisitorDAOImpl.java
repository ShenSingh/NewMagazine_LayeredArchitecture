package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Visitor;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.VisitorDAO;

import java.sql.Date;
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
                    rst.getInt("visitorNumber"),
                    rst.getString("visitorAddress"),
                    rst.getString("visitorType"),
                    rst.getString("gender"),
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

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
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
                    rst.getInt("visitorNumber"),
                    rst.getString("visitorAddress"),
                    rst.getString("visitorType"),
                    rst.getString("gender"),
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

    @Override
    public ArrayList<Visitor> getVisitorsByInput(String input) throws SQLException, ClassNotFoundException {
        ArrayList<Visitor> allVisitors = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Visitor WHERE visitorId  LIKE ?");

        while (resultSet.next()) {
            String visitorId = resultSet.getString(1);
            String visitorFirstName = resultSet.getString(2);
            String visitorLastName = resultSet.getString(3);
            Date visitorDOB = resultSet.getDate(4);
            String visitorNIC = resultSet.getString(5);
            Integer visitorNumber = resultSet.getInt(6);
            String visitorAddress = resultSet.getString(7);
            String visitorType = resultSet.getString(8);
            String gender = resultSet.getString(9);
            byte[] visitorImage = resultSet.getBytes(10);
            Visitor visitor = new Visitor(visitorId, visitorFirstName, visitorLastName, visitorDOB, visitorNIC, visitorNumber, visitorAddress, visitorType, gender,visitorImage);

            allVisitors.add(visitor);
        }

        return allVisitors;
    }
}
