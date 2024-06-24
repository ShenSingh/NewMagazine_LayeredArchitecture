package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Expences;
import lk.ijse.dao.custom.ExpencesDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpencesDAOImpl implements ExpencesDAO {
    @Override
    public ArrayList<Expences> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Expences dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Expences dto) throws SQLException, ClassNotFoundException {
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
    public Expences search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
