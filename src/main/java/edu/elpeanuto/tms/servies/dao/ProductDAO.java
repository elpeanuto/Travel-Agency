package edu.elpeanuto.tms.servies.dao;

import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.model.enums.ProductType;
import edu.elpeanuto.tms.servies.dto.ProductFilterDTO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.pagination.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * The interface that is needed to expand the BaseDAO if necessary
 * @see edu.elpeanuto.tms.servies.dao.BaseDAO
 */
public interface ProductDAO extends BaseDAO<Long, Product>, Pagination<Product> {
    boolean promote(Long id, ProductType type) throws DAOException;

    boolean delete(Long id) throws DAOException;

    Optional<Integer> minPrice() throws DAOException;

    Optional<Integer> maxPrice() throws DAOException;

    Optional<Integer> getNumberOfNotes(ProductFilterDTO filter) throws DAOException;

    List<Product> search(ProductFilterDTO filter, Integer start, Integer count, ProductType type) throws DAOException;

    Optional<Integer> getNumberOfNotes() throws DAOException;
}
