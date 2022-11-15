package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.model.Order;
import edu.elpeanuto.tms.model.Product;
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
 * Bill genarate controller.
 */
@WebServlet("/bill")
public class BillServlet extends HttpServlet {
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        try {
            Order order = orderDAO.get(id).orElseThrow(NoEntityException::new);
            Optional<Product> optionalProduct = productDAO.get(order.getProductId());

            req.setAttribute("order", order);
            req.setAttribute("product", optionalProduct.orElseThrow(NoEntityException::new));
            req.setAttribute("discount", req.getSession().getAttribute("discount"));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }

        req.getRequestDispatcher("view/user/bill.jsp").include(req, resp);
    }
}
