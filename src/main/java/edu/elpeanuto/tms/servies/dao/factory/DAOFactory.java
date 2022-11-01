package edu.elpeanuto.tms.servies.dao.factory;

import edu.elpeanuto.tms.servies.dao.*;

/**
 * Interface of DAO factory which is used in context initialization {@link edu.elpeanuto.tms.controller.listener.ContextListener}
 */
public interface DAOFactory {
    OrderDAO getOrderDAO();

    ProductDAO getProductDAO();

    UserDAO getUserDAO();

    DiscountDAO getDiscountDAO();

    MessagesDAO getMessagesDAO();
}
