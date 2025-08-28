package lk.ijse.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.Entity.Expences;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ExpencesDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT expencesId FROM Expences ORDER BY expencesId DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("expencesId");
            int newExpencesId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newExpencesId);
        } else {
            return "E00-001";
        }
    }

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

    @Override
    public Map<String, Double> getTotalCostByType() throws SQLException, ClassNotFoundException {
        String yearAndMonth = "2025/6"; // Example: "YYYY/MM"

        String[] parts = yearAndMonth.split("/");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        int previousYear = year - 1;
        String previousYearAndMonth = previousYear + "/" + month;

        ResultSet resultSet = SQLUtil.executeQuery("SELECT type, SUM(cost) AS total_cost FROM Expences WHERE month LIKE ? GROUP BY type");

        Map<String, Double> expensesByType = new HashMap<>();

        while (resultSet.next()) {
            String expenseType = resultSet.getString("type");
            double totalCost = resultSet.getDouble("total_cost");

            expensesByType.put(expenseType, totalCost);
        }

        for (Map.Entry<String, Double> entry : expensesByType.entrySet()) {
            System.out.println("Expense Type: " + entry.getKey() + ", Total Cost: " + entry.getValue());
        }
        return expensesByType;
    }

    @Override
    public Map<String, XYChart.Series<String, Number>> getExpensesDataForLineChart() throws SQLException, ClassNotFoundException {
        Map<String, XYChart.Series<String, Number>> seriesMap = new HashMap<>();

        ResultSet resultSet = SQLUtil.executeQuery("SELECT type, month, SUM(cost) AS total_cost FROM Expences GROUP BY type, month");

        while (resultSet.next()) {
            String expenseType = resultSet.getString("type");
            String month = resultSet.getString("month");
            double totalCost = resultSet.getDouble("total_cost");

            if (!seriesMap.containsKey(expenseType)) {
                seriesMap.put(expenseType, new XYChart.Series<>());
            }
            seriesMap.get(expenseType).getData().add(new XYChart.Data<>(month, totalCost));
        }
        return seriesMap;
    }
}
