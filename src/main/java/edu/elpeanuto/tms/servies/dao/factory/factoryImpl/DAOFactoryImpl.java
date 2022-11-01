package edu.elpeanuto.tms.servies.dao.factory.factoryImpl;

import edu.elpeanuto.tms.servies.dao.*;
import edu.elpeanuto.tms.servies.dao.daoImpl.*;
import edu.elpeanuto.tms.servies.dao.factory.DAOFactory;

/**
 * DAO factory implementation
 */
public class DAOFactoryImpl implements DAOFactory {
    @Override
    public OrderDAO getOrderDAO() {
        return new OrderDAOImpl();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public DiscountDAO getDiscountDAO() {
        return new DiscountDAOImpl();
    }

    @Override
    public MessagesDAO getMessagesDAO() {
        return new MessagesDAOImpl();
    }
}
