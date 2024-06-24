package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Inmate;
import lk.ijse.dao.custom.InmateDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class InmateDAOImpl implements InmateDAO {
    @Override
    public ArrayList<Inmate> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Inmate dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Inmate dto) throws SQLException, ClassNotFoundException {
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
    public Inmate search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
