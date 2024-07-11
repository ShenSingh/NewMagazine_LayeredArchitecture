package lk.ijse.dao.custom;

import lk.ijse.Entity.Section;
import lk.ijse.dao.CrudDvo;

import java.sql.SQLException;
import java.util.List;

public interface SectionDAO extends CrudDvo<Section> {
    List<Section> getJailSections() throws SQLException, ClassNotFoundException;
}
