package edu.elpeanuto.tms.servies.dao.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource pool of connections
 */
public class PoolConnectionBuilder implements DBConnection {
    private PoolConnectionBuilder() {
    }

    private static PoolConnectionBuilder instance = null;

    public static PoolConnectionBuilder getInstance() {
        if (instance == null)
            instance = new PoolConnectionBuilder();
        return instance;
    }

    public Connection getConnection() {
        try {
            Context ctx = new InitialContext();
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/travelBook");

            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            logger.error("Failed to get connection", e);
            throw new RuntimeException("Failed to get connection", e);
        }
    }
}

