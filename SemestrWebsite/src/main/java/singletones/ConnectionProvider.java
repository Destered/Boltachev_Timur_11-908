package singletones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String userName = "";
    private static final String password = "";
    private static final String url = "jdbc:postgresql://localhost:5432/website_db";
    private static final String driverClassName = "org.postgresql.Driver";

    private ConnectionProvider() throws ClassNotFoundException{
    }

    public static Connection getConnection(){
        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

}

