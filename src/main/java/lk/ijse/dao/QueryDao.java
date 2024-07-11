package lk.ijse.dao;

import lk.ijse.Entity.Inmate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QueryDao<T> extends SuperDao{
    ArrayList<Inmate> getActiveCaseInmate() throws SQLException, ClassNotFoundException;
    ArrayList<Inmate> getReleaseSoonInmates() throws SQLException, ClassNotFoundException;
}
