package edu.elpeanuto.tms.servies.dao;


import edu.elpeanuto.tms.model.Entity;
import edu.elpeanuto.tms.servies.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Base DAO interface which contains main CRUD functions
 *
 * @param <K> generic for key
 * @param <T> generic for value
 */
public interface BaseDAO<K, T extends Entity<T>> {
    Logger logger = LoggerFactory.getLogger(BaseDAO.class);

    Connection getConnection() throws SQLException;

    Optional<T> get(K id) throws DAOException;

    List<T> getAll() throws DAOException;

    boolean save(T t) throws DAOException;

    boolean update(T t) throws DAOException;
}
