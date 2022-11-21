package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.servies.alert.AlertType;
import edu.elpeanuto.tms.servies.alert.SetAlertToRequest;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.FailToUpdateDBException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.slf4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    private Logger logger;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    @Override
    public void init(ServletConfig config) {
        ServletContext sc = config.getServletContext();

        productDAO = (ProductDAO) sc.getAttribute("productDAO");
        orderDAO = (OrderDAO) sc.getAttribute("orderDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String adminProductName = req.getParameter("name");

        try {
            if (!adminProductName.equals(productDAO.get(id).orElseThrow(NoEntityException::new).getName())) {
                SetAlertToRequest.setCustomAlert(req, "Error", "Names are not same.", AlertType.ERROR);
                resp.sendRedirect(String.format("adminView?id=%d", id));
                return;
            }

            if (orderDAO.isProductOrdered(id)) {
                SetAlertToRequest.setCustomAlert(req, "Error", "This tour is already in use.", AlertType.ERROR);
                resp.sendRedirect(String.format("adminView?id=%d", id));
                return;
            }

            if (!productDAO.delete(id))
                throw new FailToUpdateDBException();

            resp.sendRedirect("allProductEdit?page=1");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setCustomAlert(req, "Error", e.getMessage(), AlertType.ERROR);

            resp.sendRedirect("adminHome");
        } catch (NoEntityException | FailToUpdateDBException e) {
            logger.warn(e.getMessage());
            SetAlertToRequest.setCustomAlert(req, "Warning", e.getMessage(), AlertType.WARNING);
        }
    }
}
