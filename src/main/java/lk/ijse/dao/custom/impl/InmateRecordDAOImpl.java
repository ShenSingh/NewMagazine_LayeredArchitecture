package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.InmateRecord;
import lk.ijse.dao.custom.InmateRecordDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class InmateRecordDAOImpl implements InmateRecordDAO {
    @Override
    public ArrayList<InmateRecord> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(InmateRecord dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(InmateRecord dto) throws SQLException, ClassNotFoundException {
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
    public InmateRecord search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
