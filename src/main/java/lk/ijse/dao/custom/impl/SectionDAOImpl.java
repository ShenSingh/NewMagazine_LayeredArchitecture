package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Section;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SectionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectionDAOImpl implements SectionDAO {
    @Override
    public ArrayList<Section> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Section");

        ArrayList<Section> allSections = new ArrayList<>();
        while (rst.next()) {
            Section section = new Section(
                    rst.getString("sectionId"),
                    rst.getString("sectionName"),
                    rst.getString("location"),
                    rst.getInt("capacity"),
                    rst.getString("securityLevel"),
                    rst.getString("status")
            );
            allSections.add(section);
        }
        return allSections;
    }

    @Override
    public boolean save(Section dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("INSERT INTO Section VALUES (?,?,?,?,?,?)",
                dto.getSectionId(), dto.getSectionName(), dto.getLocation(), dto.getCapacity(), dto.getSecurityLevel(), dto.getStatus());
    }

    @Override
    public boolean update(Section dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("UPDATE Section SET sectionName=?, location=?, capacity=?, securityLevel=?, status=? WHERE sectionId=?",
                dto.getSectionName(), dto.getLocation(), dto.getCapacity(), dto.getSecurityLevel(), dto.getStatus(), dto.getSectionId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("DELETE FROM Section WHERE sectionId=?", id);
    }

//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//        return null;
//    }

    @Override
    public Section search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Section WHERE sectionId=?", id);
        if (rst.next()) {
            return new Section(
                    rst.getString("sectionId"),
                    rst.getString("sectionName"),
                    rst.getString("location"),
                    rst.getInt("capacity"),
                    rst.getString("securityLevel"),
                    rst.getString("status")
            );
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT sectionId FROM Section WHERE sectionId=?", id);
        return rst.next();
    }
}
