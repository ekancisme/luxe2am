package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://160.187.146.251:3306/PKManagement?serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "luxe2am";
    private static final String PASSWORD = "I1pQeAvbGfGon";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
