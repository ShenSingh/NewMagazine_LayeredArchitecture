package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Officer;
import lk.ijse.dao.custom.OfficerDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OfficerDAOImpl implements OfficerDAO {
    @Override
    public ArrayList<Officer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Officer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Officer dto) throws SQLException, ClassNotFoundException {
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
    public Officer search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
