package edu.elpeanuto.tms.servies.dao;

import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.servies.dto.ProductFilterDTO;
import edu.elpeanuto.tms.servies.exception.DAOException;

import java.util.List;
import java.util.Optional;

/**
 * The interface that is needed to expand the BaseDAO if necessary
 * @see edu.elpeanuto.tms.servies.dao.BaseDAO
 */
public interface ProductDAO extends BaseDAO<Long, Product> {
    boolean promote(Long id, String type) throws DAOException;

    Optional<Integer> minPrice() throws DAOException;

    Optional<Integer> maxPrice() throws DAOException;

    Optional<Integer> getNumberOfNotes(ProductFilterDTO filter) throws DAOException;

    List<Product> search(ProductFilterDTO filter, Integer start, Integer count, String type) throws DAOException;

    List<Product> getPagination(Integer start, Integer numOfStrings) throws DAOException;

    Optional<Integer> getNumberOfNotes() throws DAOException;
}
