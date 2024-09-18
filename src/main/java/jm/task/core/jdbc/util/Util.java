package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USERNAME = "bestuser";
    private static final String PASSWORD = "bestuser";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
