package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Officer;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OfficerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfficerDAOImpl implements OfficerDAO {
    @Override
    public ArrayList<Officer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Officer");

        ArrayList<Officer> allOfficers = new ArrayList<>();
        while (rst.next()){
            Officer officer = new Officer(
                    rst.getString("officerId"),
                    rst.getString("officerFirstName"),
                    rst.getString("officerLastName"),
                    rst.getDate("officerDOB"),
                    rst.getString("officerNIC"),
                    rst.getString("gender"),
                    rst.getString("officerAddress"),
                    rst.getString("officerEmail"),
                    rst.getString("officerNumber"),
                    rst.getString("position"),
                    rst.getString("sectionId"),
                    rst.getDouble("salary")
            );
            allOfficers.add(officer);
        }
        return allOfficers;
    }

    @Override
    public boolean save(Officer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("INSERT INTO Officer VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                dto.getOfficerId(),
                dto.getOfficerFirstName(),
                dto.getOfficerLastName(),
                dto.getOfficerDOB(),
                dto.getOfficerNIC(),
                dto.getGender(),
                dto.getOfficerAddress(),
                dto.getOfficerEmail(),
                dto.getOfficerNumber(),
                dto.getPosition(),
                dto.getSectionId(),
                dto.getSalary()
        );
    }

    @Override
    public boolean update(Officer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("UPDATE Officer SET officerFirstName=?, officerLastName=?, officerDOB=?, officerNIC=?, gender=?, officerAddress=?, officerEmail=?, officerNumber=?, position=?, sectionId=?, salary=? WHERE officerId=?",
                dto.getOfficerFirstName(),
                dto.getOfficerLastName(),
                dto.getOfficerDOB(),
                dto.getOfficerNIC(),
                dto.getGender(),
                dto.getOfficerAddress(),
                dto.getOfficerEmail(),
                dto.getOfficerNumber(),
                dto.getPosition(),
                dto.getSectionId(),
                dto.getSalary(),
                dto.getOfficerId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("DELETE FROM Officer WHERE officerId=?", id);
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
    public Officer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Officer WHERE officerId=?", id);
        rst.next();
        return new Officer(
                id,
                rst.getString("officerFirstName"),
                rst.getString("officerLastName"),
                rst.getDate("officerDOB"),
                rst.getString("officerNIC"),
                rst.getString("gender"),
                rst.getString("officerAddress"),
                rst.getString("officerEmail"),
                rst.getString("officerNumber"),
                rst.getString("position"),
                rst.getString("sectionId"),
                rst.getDouble("salary")
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT officerId FROM Officer WHERE officerId=?", id);
        return rst.next();
    }
}
