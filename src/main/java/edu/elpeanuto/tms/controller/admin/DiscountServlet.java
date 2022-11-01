package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.model.Discount;
import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.servies.dao.DiscountDAO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.FailToUpdateDBException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.slf4j.Logger;

import javax.security.auth.login.FailedLoginException;
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
 * Discount set controller
 */
@WebServlet("/discount")
public class DiscountServlet extends HttpServlet {
    private Logger logger;
    DiscountDAO discountDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        discountDAO = (DiscountDAO) sc.getAttribute("discountDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            discountDAO.get(1L).ifPresent(value -> req.setAttribute("discount", value));
        } catch (DAOException e) {
            logger.error(e.getMessage());
        }

        req.getRequestDispatcher("view/admin/discount.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String step = req.getParameter("step");
        String max = req.getParameter("max");

        Discount discount = new Discount(1L, Integer.parseInt(step), Integer.parseInt(max));

        try {
            if(!discountDAO.update(discount))
                throw new FailToUpdateDBException();
        } catch (DAOException e) {
            logger.error(e.getMessage());
        }catch (FailToUpdateDBException e) {
            logger.warn(e.getMessage());
        }

        resp.sendRedirect("discount");
    }

}
