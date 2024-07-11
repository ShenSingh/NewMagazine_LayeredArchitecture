package lk.ijse.dao.custom;

import lk.ijse.Entity.Visitor;
import lk.ijse.dao.CrudDvo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VisitorDAO extends CrudDvo<Visitor> {
    ArrayList<Visitor> getVisitorsByInput(String input) throws SQLException, ClassNotFoundException;
}
