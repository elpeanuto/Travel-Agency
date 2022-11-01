package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.model.Order;
import edu.elpeanuto.tms.model.enums.OrderStatus;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
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
import java.util.List;
import java.util.stream.Stream;

/**
 * Controller for displaying all orders and changing their status
 */
@WebServlet("/allOrders")
public class AllOrderServlet extends HttpServlet {
    private Logger logger;
    private OrderDAO orderDAO;

    private Integer numOfStringOnPage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        orderDAO = (OrderDAO) sc.getAttribute("orderDAO");
        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage=3;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("orderList", pagination(req, numOfStringOnPage));
        } catch (DAOException | NoEntityException e) {
            logger.error(e.getMessage());
        }

        req.getRequestDispatcher("view/admin/ordersReceived.jsp").include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String status = req.getParameter("status");
        try {
            Order order = orderDAO.get(id).orElseThrow(NoEntityException::new);

            if(order.getStatus().equals(OrderStatus.Registered.name()) && status.equals(OrderStatus.Succeed.name())){
                resp.sendRedirect("allOrders?page=1");
                return;
            }
            if(order.getStatus().equals(OrderStatus.Paid.name()) && status.equals(OrderStatus.Canceled.name())){
                resp.sendRedirect("allOrders?page=1");
                return;
            }
            if (!orderDAO.changeStatus(id, status))
                throw new FailToUpdateDBException();
        } catch (DAOException | NoEntityException e) {
            logger.error(e.getMessage());
        } catch (FailToUpdateDBException e) {
            logger.warn(e.getMessage());
        }

        resp.sendRedirect("allOrders?page=1");
    }

    private List<Order> pagination(HttpServletRequest request, Integer numOfStrings) throws DAOException, NoEntityException {
        int page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("positionList", pages(numOfStrings));

        return orderDAO.getPaginationNotProceeded(page * numOfStrings - numOfStrings, numOfStrings);
    }

    private List<Integer> pages(Integer numOfStrings) throws DAOException, NoEntityException {
        int pagesLimit = (int) Math.ceil(orderDAO.getNumberOfNotesNotProceeded().orElseThrow(NoEntityException::new) / (double) numOfStrings);
        List<Integer> list = Stream.iterate(1, n -> n + 1).limit(pagesLimit).toList();

        return list.size() != 1 ? list : null;
    }
}
