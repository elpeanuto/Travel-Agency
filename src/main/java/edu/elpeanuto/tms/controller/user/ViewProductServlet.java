package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.model.Product;
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
 * Tour details output controller
 */
@WebServlet("/viewProduct")
public class ViewProductServlet extends HttpServlet {
    private Logger logger;
    private ProductDAO productDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        productDAO = (ProductDAO) sc.getAttribute("productDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        try {
            req.setAttribute("product", productDAO.get(id).orElseThrow(NoEntityException::new));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }

        req.getRequestDispatcher("view/user/viewProduct.jsp").include(req,resp);
    }
}
