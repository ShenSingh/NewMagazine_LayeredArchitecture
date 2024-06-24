package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Visitor;
import lk.ijse.dao.custom.VisitorDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class VisitorDAOImpl implements VisitorDAO {
    @Override
    public ArrayList<Visitor> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Visitor dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Visitor dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Visitor search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
