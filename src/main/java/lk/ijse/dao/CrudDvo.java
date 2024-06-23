package lk.ijse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDvo<T> extends SuperDao{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNewId() throws SQLException, ClassNotFoundException;
    T search(String id) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
