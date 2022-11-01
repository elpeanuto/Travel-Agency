package edu.elpeanuto.tms.servies.dao;

import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.servies.exception.DAOException;

import java.util.List;
import java.util.Optional;

/**
 * The interface that is needed to expand the BaseDAO if necessary
 * @see edu.elpeanuto.tms.servies.dao.BaseDAO
 */
public interface UserDAO extends BaseDAO<Long, User> {
    boolean userVerify(String email, String password) throws DAOException;

    boolean isEmailOccupied(String email) throws DAOException;

    boolean resetPassword(String email, String password) throws DAOException;

    boolean promote(Long id, String status) throws DAOException;

    Optional<User> get(String email) throws DAOException;

    List<User> getPaginationByStatus(Integer start, Integer numOfStrings, String status)  throws DAOException;

    Optional<Integer> getNumberOfNotesByStatus(String status) throws DAOException;
}

