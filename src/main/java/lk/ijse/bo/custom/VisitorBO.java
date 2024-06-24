package lk.ijse.bo.custom;

import lk.ijse.Model.VisitorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VisitorBO extends SuperBo{
    ArrayList<VisitorDTO> getAllVisitor() throws SQLException, ClassNotFoundException;

    boolean saveVisitor(VisitorDTO visitorDTO) throws SQLException, ClassNotFoundException;

    void updateVisitor(VisitorDTO visitorDTO) throws SQLException, ClassNotFoundException;

    void deleteVisitor(String id) throws SQLException, ClassNotFoundException;

    //String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existVisitor(String id) throws SQLException, ClassNotFoundException;
}
