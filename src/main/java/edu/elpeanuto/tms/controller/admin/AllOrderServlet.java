package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.model.Order;
import edu.elpeanuto.tms.model.enums.OrderStatus;
import edu.elpeanuto.tms.servies.alert.AlertType;
import edu.elpeanuto.tms.servies.alert.SetAlertToRequest;
import edu.elpeanuto.tms.servies.pagination.SimplePagination;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
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
 * Controller for displaying all orders and changing their status
 */
@WebServlet("/allOrders")
public class AllOrderServlet extends HttpServlet {
    private Logger logger;
    private OrderDAO orderDAO;
    private Integer numOfStringOnPage;

    @Override
    public void init(ServletConfig config) {
        ServletContext sc = config.getServletContext();

        orderDAO = (OrderDAO) sc.getAttribute("orderDAO");
        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage = 12;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("orderList", SimplePagination.pagination(orderDAO, req, numOfStringOnPage));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setCustomAlert(req, "Error", e.getMessage(), AlertType.ERROR);

            resp.sendRedirect("adminHome");
            return;
        }catch (NoEntityException e) {
            logger.warn(e.getMessage());
            SetAlertToRequest.setCustomAlert(req, "Error", e.getMessage(), AlertType.WARNING);
        }

        req.getRequestDispatcher("view/admin/ordersReceived.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String status = req.getParameter("status");
        try {
            Order order = orderDAO.get(id).orElseThrow(NoEntityException::new);

            if (order.getStatus().equals(OrderStatus.Registered) && status.equals(OrderStatus.Succeed.name())) {
                SetAlertToRequest.setCustomAlert(req, "Error", "This tour has not been paid yet.", AlertType.ERROR);
                resp.sendRedirect("allOrders?page=1");
                return;
            }
            if (order.getStatus().equals(OrderStatus.Paid) && status.equals(OrderStatus.Canceled.name())) {
                SetAlertToRequest.setCustomAlert(req, "Error", "Paid tour cannot be canceled", AlertType.ERROR);
                resp.sendRedirect("allOrders?page=1");
                return;
            }
            if (!orderDAO.changeStatus(id, OrderStatus.valueOf(status)))
                throw new FailToUpdateDBException();
        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setCustomAlert(req, "Error", e.getMessage(), AlertType.ERROR);
        }catch (NoEntityException | FailToUpdateDBException e) {
            logger.warn(e.getMessage());
            SetAlertToRequest.setCustomAlert(req, "Warning", e.getMessage(), AlertType.WARNING);
        }

        resp.sendRedirect("allOrders?page=1");
    }
}
