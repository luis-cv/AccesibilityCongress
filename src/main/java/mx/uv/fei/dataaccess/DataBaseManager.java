package mx.uv.fei.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
public class DataBaseManager {
    private Connection connection;
    private final String DATABASE_NAME = "jdbc:mariadb://localhost:3306/congress";
    private final String DATABASE_USER = "systemUser";
    private final String DATABASE_PASSWORD = "AccsCongSys";

    public Connection getConnection() throws SQLException {
        connect();
        return connection;
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_NAME,DATABASE_USER,DATABASE_PASSWORD);
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                if (connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException exception) {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
    }
}