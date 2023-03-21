package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "saochilang97";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/projectjava";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER,DB_PASSWORD);
    }
}
