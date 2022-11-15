package edu.elpeanuto.tms.controller.listener;

import edu.elpeanuto.tms.servies.dao.factory.DAOFactory;
import edu.elpeanuto.tms.servies.dao.factory.factoryImpl.DAOFactoryImpl;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener that configure context of program.
 */
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory daoFactory = new DAOFactoryImpl();
        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userDAO", daoFactory.getUserDAO());
        servletContext.setAttribute("orderDAO", daoFactory.getOrderDAO());
        servletContext.setAttribute("productDAO", daoFactory.getProductDAO());
        servletContext.setAttribute("discountDAO", daoFactory.getDiscountDAO());
        servletContext.setAttribute("messagesDAO", daoFactory.getMessagesDAO());

        servletContext.setAttribute("logger", LoggerFactory.getLogger(ContextListener.class));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
