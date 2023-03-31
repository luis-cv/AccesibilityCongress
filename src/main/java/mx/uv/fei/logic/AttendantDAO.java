package mx.uv.fei.logic;

import mx.uv.fei.dataaccess.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AttendantDAO implements IAttendant {
    public int addAttendant(Attendant attendant) throws SQLException {
        int result;
        String sqlQuery = "INSERT INTO Attendants (AttendantName, AttendantLastName, Email, EventAssist) VALUES (?,?,?,?)";

        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setString(1, attendant.getAttendantName());
        preparedStatement.setString(2, attendant.getLastname());
        preparedStatement.setString(3, attendant.getEmail());
        preparedStatement.setString(4, attendant.getEventAssist());

        result = preparedStatement.executeUpdate();
        dataBaseManager.closeConnection();

        return result;
    }
}
