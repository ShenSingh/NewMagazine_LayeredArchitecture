package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Expences;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ExpencesDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpencesDAOImpl implements ExpencesDAO {
    @Override
    public ArrayList<Expences> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Expences");
        ArrayList<Expences> getAllExpences=new ArrayList<>();
        while (rst.next()){
            Expences entity= new Expences(
                    rst.getString("expencesId"),
                    rst.getString("sectionId"),
                    rst.getString("month"),
                    rst.getString("type"),
                    rst.getDouble("cost")
            );
            getAllExpences.add(entity);
        }
        return getAllExpences;
    }

    @Override
    public boolean save(Expences dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("INSERT INTO Expences (expencesId,sectionId,month,type,cost) VALUES (?,?,?,?,?)",
                dto.getExpencesID(),dto.getSectionId(),dto.getMonth(),dto.getType(),dto.getCost());
    }

    @Override
    public boolean update(Expences dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("UPDATE Expences SET sectionId=?, month=?, type=?, cost=? WHERE expencesId=?",
                dto.getSectionId(),dto.getMonth(),dto.getType(),dto.getCost(),dto.getExpencesID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("DELETE FROM Expences WHERE expencesId=?",id);
    }

//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//        return null;
//    }

    @Override
    public Expences search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Expences WHERE expencesId=?",id);
        resultSet.next();
        return new Expences(
                id,
                resultSet.getString("sectionId"),
                resultSet.getString("month"),
                resultSet.getString("type"),
                resultSet.getDouble("cost")
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeQuery("SELECT expencesId FROM Expences WHERE expencesId=?",id);
        return resultSet.next();
    }
}
