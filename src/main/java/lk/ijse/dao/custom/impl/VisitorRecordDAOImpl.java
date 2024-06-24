package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.VisitorRecord;
import lk.ijse.dao.custom.VisitorRecordDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class VisitorRecordDAOImpl implements VisitorRecordDAO {
    @Override
    public ArrayList<VisitorRecord> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(VisitorRecord dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(VisitorRecord dto) throws SQLException, ClassNotFoundException {
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
    public VisitorRecord search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
