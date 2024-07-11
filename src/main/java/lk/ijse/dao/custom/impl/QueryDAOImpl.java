package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Inmate;
import lk.ijse.dao.QueryDao;
import lk.ijse.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDao {

    @Override
    public ArrayList<Inmate> getActiveCaseInmate() throws SQLException, ClassNotFoundException {
        ArrayList<Inmate> inmates = new ArrayList<>();

        String query = "SELECT i.inmateId, " +
                "i.inmateFirstName, " +
                "i.inmateLastName, " +
                "i.inmateDOB, " +
                "i.inmateNIC, " +
                "i.gender, " +
                "i.inmateAddress, " +
                "i.status " +
                "FROM Inmate i " +
                "JOIN InmateRecord ir ON " +
                "i.inmateId = ir.inmateId WHERE i.status = 'Active' " +
                "AND ir.caseStatus = 'Ongoing'";
        ResultSet resultSet = SQLUtil.executeQuery(query);

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            LocalDate dob = LocalDate.parse(resultSet.getString(4));
            String nic = resultSet.getString(5);
            String gender = resultSet.getString(6);
            String address = resultSet.getString(7);
            String status = resultSet.getString(8);

            Inmate inmate = new Inmate(id, firstName, lastName, dob, nic, gender, address, status, byte[].class.cast(null));
            inmates.add(inmate);
        }
        return inmates;
    }

    @Override
    public ArrayList<Inmate> getReleaseSoonInmates() throws SQLException, ClassNotFoundException {
        ArrayList<Inmate> inmates = new ArrayList<>();

        String query = "SELECT i.inmateId, " +
                "i.inmateFirstName, " +
                "i.inmateLastName, " +
                "i.inmateDOB, " +
                "i.inmateNIC, " +
                "i.gender, " +
                "i.inmateAddress, " +
                "i.status " +
                "FROM Inmate i " +
                "JOIN InmateRecord ir ON " +
                "i.inmateId = ir.inmateId WHERE " +
                "(YEAR(ir.releaseDate) = YEAR(CURDATE()) AND " +
                "MONTH(ir.releaseDate) = MONTH(CURDATE()))";

        ResultSet resultSet = SQLUtil.executeQuery(query);

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            LocalDate dob = LocalDate.parse(resultSet.getString(4));
            String nic = resultSet.getString(5);
            String gender = resultSet.getString(6);
            String address = resultSet.getString(7);
            String status = resultSet.getString(8);

            Inmate inmate = new Inmate(id, firstName, lastName, dob, nic, gender, address, status, byte[].class.cast(null));
            inmates.add(inmate);
        }

        return inmates;
    }
}
