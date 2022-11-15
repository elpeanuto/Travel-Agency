package edu.elpeanuto.tms.servies.pagination;

import edu.elpeanuto.tms.model.Entity;
import edu.elpeanuto.tms.servies.exception.DAOException;

import java.util.List;
import java.util.Optional;

/**
 * Simple pagination without specify params
 * @param <T> Any class extended from Entity
 */
public interface Pagination<T extends Entity<T>> {
    Optional<Integer> getNumberOfNotes() throws DAOException;
    List<T> getPagination(Integer start, Integer numOfStrings) throws DAOException;
}
