package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Section;
import lk.ijse.dao.custom.SectionDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SectionDAOImpl implements SectionDAO {
    @Override
    public ArrayList<Section> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Section dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Section dto) throws SQLException, ClassNotFoundException {
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
    public Section search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
