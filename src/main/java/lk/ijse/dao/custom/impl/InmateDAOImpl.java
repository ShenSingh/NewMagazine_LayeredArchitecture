package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Inmate;
import lk.ijse.Model.InmateDTO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.InmateDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InmateDAOImpl implements InmateDAO {
    @Override
    public ArrayList<Inmate> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Inmate");
        ArrayList<Inmate> getAllInmate = new ArrayList<>();

        while (rst.next()){
            Inmate entity = new Inmate(
                    rst.getString("inmateId"),
                    rst.getString("inmateFirstName"),
                    rst.getString("inmateLastName"),
                    rst.getDate("inmateDOB").toLocalDate(),
                    rst.getString("inmateNIC"),
                    rst.getString("gender"),
                    rst.getString("inmateAddress"),
                    rst.getString("status"),
                    rst.getBytes("inmateImage")
                    );
            getAllInmate.add(entity);
        }
        return getAllInmate;
    }

    @Override
    public boolean save(Inmate dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("INSERT INTO Inmate VALUES (?,?,?,?,?,?,?,?)",
                dto.getInmateId(),
                dto.getInmateFirstName(),
                dto.getInmateLastName(),
                dto.getInmateDOB(),
                dto.getInmateNIC(),
                dto.getGender(),
                dto.getInmateAddress(),
                dto.getStatus(),
                dto.getInmateImage());
    }

    @Override
    public boolean update(Inmate dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("UPDATE Inmate SET inmateFirstName=?, inmateLastName=?, inmateDOB=?, inmateNIC=?, gender=?, inmateAddress=?, status=?, inmateImage=? WHERE inmateId=?",
                dto.getInmateFirstName(),
                dto.getInmateLastName(),
                dto.getInmateDOB(),
                dto.getInmateNIC(),
                dto.getGender(),
                dto.getInmateAddress(),
                dto.getStatus(),
                dto.getInmateImage(),
                dto.getInmateId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("DELETE FROM Inmate WHERE inmateId=?",id);
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
    public Inmate search(String id) throws SQLException, ClassNotFoundException {
    ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Inmate WHERE inmateId=?",id);
        resultSet.next();
        return new Inmate(
                id,
                resultSet.getString("inmateFirstName"),
                resultSet.getString("inmateLastName"),
                resultSet.getDate("inmateDOB").toLocalDate(),
                resultSet.getString("inmateNIC"),
                resultSet.getString("gender"),
                resultSet.getString("inmateAddress"),
                resultSet.getString("status"),
                resultSet.getBytes("inmateImage")
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT inmateId FROM Inmate WHERE inmateId=?",id);
        return resultSet.next();
    }

    @Override
    public ArrayList<InmateDTO> getInmatesByGender(String genderType) throws Exception {

        ArrayList<Inmate> allInmate = getAll();
        ArrayList<InmateDTO> allInmates = new ArrayList<>();

        for (Inmate inmate : allInmate) {

            if (!inmate.getGender().equals(genderType)){
                continue;
            }
            allInmates.add(new InmateDTO(inmate.getInmateId(),inmate.getInmateFirstName(),inmate.getInmateLastName(),inmate.getInmateDOB(),inmate.getInmateNIC(),inmate.getGender(),inmate.getInmateAddress(),inmate.getStatus(),inmate.getInmateImage()));
        }
        return allInmates;
    }

    @Override
    public ArrayList<InmateDTO> getActiveInmates() throws SQLException, ClassNotFoundException {
        ArrayList<Inmate> allInmate = getAll();
        ArrayList<InmateDTO> allInmates = new ArrayList<>();

        for (Inmate inmate : allInmate) {

            if (!inmate.getStatus().equals("Active")){
                continue;
            }
            allInmates.add(new InmateDTO(inmate.getInmateId(),inmate.getInmateFirstName(),inmate.getInmateLastName(),inmate.getInmateDOB(),inmate.getInmateNIC(),inmate.getGender(),inmate.getInmateAddress(),inmate.getStatus(),inmate.getInmateImage()));
        }
        return allInmates;
    }
}
