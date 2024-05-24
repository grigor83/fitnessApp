package savjetnikApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/fitness_app";
    private static final String USER = "root";
    private static final String PASSWORD = "mojabaza";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";  // MySQL JDBC driver
    
	public DatabaseConnection() { }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);  // Load the JDBC driver
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
