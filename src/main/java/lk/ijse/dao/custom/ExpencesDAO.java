package lk.ijse.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.Entity.Expences;
import lk.ijse.dao.CrudDvo;

import java.sql.SQLException;
import java.util.Map;

public interface ExpencesDAO extends CrudDvo<Expences> {
    Map<String, Double> getTotalCostByType() throws SQLException, ClassNotFoundException;
    Map<String, XYChart.Series<String, Number>> getExpensesDataForLineChart() throws SQLException, ClassNotFoundException;
}
