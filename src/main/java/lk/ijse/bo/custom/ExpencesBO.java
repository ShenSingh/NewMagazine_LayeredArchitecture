package lk.ijse.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.Model.ExpencesDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface ExpencesBO extends SuperBo{
    ArrayList<ExpencesDTO> getAllExpences() throws SQLException, ClassNotFoundException;

    boolean saveExpences(ExpencesDTO expencesDTO) throws SQLException, ClassNotFoundException;

    void updateExpences(ExpencesDTO expencesDTO) throws SQLException, ClassNotFoundException;

    void deleteExpences(String id) throws SQLException, ClassNotFoundException;

    String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existExpences(String id) throws SQLException, ClassNotFoundException;

    Map<String, Double> getTotalCostByType() throws SQLException, ClassNotFoundException;

    Map<String, XYChart.Series<String, Number>> getExpensesDataForLineChart() throws SQLException, ClassNotFoundException;
}
