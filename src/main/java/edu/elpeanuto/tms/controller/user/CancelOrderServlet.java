package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.model.enums.OrderStatus;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.slf4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Cancel order controller.
 */
@WebServlet("/cancelOrder")
public class CancelOrderServlet extends HttpServlet {
    private Logger logger;

    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        orderDAO = (OrderDAO) sc.getAttribute("orderDAO");
        productDAO = (ProductDAO) sc.getAttribute("productDAO");

        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("id"));

        try {
            Optional<Product> optionalProduct = productDAO.get(orderDAO.get(orderId).orElseThrow(NoEntityException::new).getProductId());

            req.setAttribute("orderId", orderId);
            req.setAttribute("product", optionalProduct.isPresent());
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }

        req.getRequestDispatcher("view/user/cancelOrder.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("id"));

        try {
            if (orderDAO.get(orderId).orElseThrow(NoEntityException::new).getStatus().equals(OrderStatus.Registered))
                orderDAO.changeStatus(orderId, OrderStatus.Canceled);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }

        resp.sendRedirect("myOrders?page=1");
    }
}