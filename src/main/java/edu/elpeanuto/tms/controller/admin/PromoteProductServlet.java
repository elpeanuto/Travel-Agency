package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.model.enums.ProductType;
import edu.elpeanuto.tms.servies.pagination.SimplePagination;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.FailToUpdateDBException;
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

/**
 * Tour status change controller.
 */
@WebServlet("/promoteProduct")
public class PromoteProductServlet extends HttpServlet {
    private Logger logger;
    private ProductDAO productDAO;

    private Integer numOfStringOnPage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        productDAO = (edu.elpeanuto.tms.servies.dao.ProductDAO) sc.getAttribute("productDAO");
        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage = 12;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("productList", SimplePagination.pagination(productDAO, req, numOfStringOnPage));
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (NoEntityException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("view/admin/promoteProduct.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ProductType status = ProductType.valueOf(req.getParameter("status"));

        try {
            if (!productDAO.promote(id, status))
                throw new FailToUpdateDBException();
        } catch (DAOException e) {
            logger.error(e.getMessage());
        } catch (FailToUpdateDBException e) {
            logger.warn(e.getMessage());
        }

        resp.sendRedirect("promoteProduct?page=1");
    }
}