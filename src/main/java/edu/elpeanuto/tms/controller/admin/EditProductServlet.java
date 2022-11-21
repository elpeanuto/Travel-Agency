package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.servies.alert.AlertType;
import edu.elpeanuto.tms.servies.alert.SetAlertToRequest;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.servies.exception.DAOException;
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
 * Tour change controller.
 */
@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {
    private Logger logger;
    private ProductDAO productDAO;

    @Override
    public void init(ServletConfig config) {
        ServletContext sc = config.getServletContext();

        productDAO = (ProductDAO) sc.getAttribute("productDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Optional<Product> product;

        try {
            product = productDAO.get(id);
            product.ifPresent(value -> req.setAttribute("product", value));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setCustomAlert(req, "Error", e.getMessage(), AlertType.ERROR);

            resp.sendRedirect("adminHome");
            return;
        }

        req.getRequestDispatcher("view/admin/editProduct.jsp").include(req,resp);
    }
}
