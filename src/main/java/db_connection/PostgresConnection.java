package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresConnection {

    private static final Logger LOGGER = Logger.getLogger(PostgresConnection .class.getName());
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            String url = "jdbc:postgresql://localhost/postgres";
            String user = "postgres";
            String password = "";

            try {
                connection=DriverManager.getConnection(url, user, password);
            } catch (SQLException sqlException) {
                LOGGER.log(Level.SEVERE, null, sqlException);
            }
        }
        return connection;
    }
}
