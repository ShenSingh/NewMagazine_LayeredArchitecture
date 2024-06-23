package lk.ijse.dao;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T executeQuery(String sql , Object... ar) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < ar.length; i++){
            preparedStatement.setObject((i+1) , ar[i]);
        }

        if (sql.startsWith("SELECT")){
            return (T)preparedStatement.executeQuery();
        }else {

            return (T)(Boolean)(preparedStatement.executeUpdate() > 0);
        }
    }
}
