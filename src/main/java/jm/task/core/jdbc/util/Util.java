package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USERNAME = "bestuser";
    private static final String PASSWORD = "bestuser";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

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

    public static SessionFactory getConfiguration() {
        return new Configuration()
                .addAnnotatedClass(User.class)
                .setProperty("connection.driver_class", DRIVER)
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.username", USERNAME)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("show_sql", "true")
                .buildSessionFactory();
    }




}
