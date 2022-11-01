package edu.elpeanuto.tms.servies.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection implements DBConnection{
    private final String user = "root";
    private final String password = "141312";
    private final String url = "jdbc:mysql://localhost:3306/travelbooktest";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to get connection", e);
            throw new RuntimeException("Failed to get connection", e);
        }

        return DriverManager.getConnection(url, user, password);
    }
}
