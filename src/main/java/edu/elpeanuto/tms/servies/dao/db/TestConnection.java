package edu.elpeanuto.tms.servies.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection by jdbc driver used for tests
 */
public class TestConnection implements DBConnection{

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to get connection", e);
            throw new RuntimeException("Failed to get connection", e);
        }

        String user = "root";
        String password = "141312";
        String url = "jdbc:mysql://localhost:3306/travelbooktest";
        return DriverManager.getConnection(url, user, password);
    }
}
