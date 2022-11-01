package edu.elpeanuto.tms.servies.dao.db;

import edu.elpeanuto.tms.servies.dao.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * PoolConnectionBuilder api
 */
public interface DBConnection {
    Logger logger = LoggerFactory.getLogger(DBConnection.class);

    Connection getConnection() throws SQLException;
}
