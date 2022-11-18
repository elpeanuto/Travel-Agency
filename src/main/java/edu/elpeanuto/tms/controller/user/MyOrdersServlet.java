package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.model.Order;
import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.servies.alert.SetAlertToRequest;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.dto.OrderDTO;
import edu.elpeanuto.tms.servies.dto.UserDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Clients output order controller.
 */
@WebServlet("/myOrders")
public class MyOrdersServlet extends HttpServlet {
    private Logger logger;

    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    private Integer numOfStringOnPage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        orderDAO = (OrderDAO) sc.getAttribute("orderDAO");
        productDAO = (ProductDAO) sc.getAttribute("productDAO");

        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage = 5;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList;
        List<OrderDTO> orderDTOList = new ArrayList<>();

        Product product;
        try {
            orderList = pagination(req, numOfStringOnPage, ((UserDTO) req.getSession().getAttribute("user")).getId());

            for (Order order : orderList) {

                product = productDAO.get(order.getProductId()).orElseThrow(NoEntityException::new);
                orderDTOList.add(new OrderDTO(order, product.getName(), product.getCountry(), product.getCity(), order.getTotalPrice()));
            }

        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);

            resp.sendRedirect("allProduct?page=1");
            return;
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);
        }

        req.setAttribute("orderList", orderDTOList);
        req.getRequestDispatcher("view/user/myOrders.jsp").include(req, resp);
    }

    /**
     * Get data by pieces from db and set pageList.
     * @param request Http servlet request.
     * @param numOfStrings Number of string on one page.
     * @param userId User id.
     * @return List of orders.
     * @throws DAOException Exception: {@link edu.elpeanuto.tms.servies.exception.DAOException check}
     * @throws NoEntityException Exception: {@link edu.elpeanuto.tms.servies.exception.NoEntityException check}
     */
    private List<Order> pagination(HttpServletRequest request, Integer numOfStrings, Long userId) throws DAOException, NoEntityException {
        int page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("positionList", pages(numOfStrings, userId));

        return orderDAO.getPaginationByUserId(page * numOfStrings - numOfStrings, numOfStrings, userId);
    }

    /**
     * Create page list.
     * @param numOfStrings Number of string on one page.
     * @param userId User id.
     * @return List of orders.
     * @throws DAOException Exception: {@link edu.elpeanuto.tms.servies.exception.DAOException check}
     * @throws NoEntityException Exception: {@link edu.elpeanuto.tms.servies.exception.NoEntityException check}
     */
    private List<Integer> pages(Integer numOfStrings, Long userId) throws DAOException, NoEntityException {
        int pagesLimit = (int) Math.ceil(orderDAO.getNumberOfNotesByUserId(userId).orElseThrow(NoEntityException::new) / (double) numOfStrings);
        List<Integer> list = Stream.iterate(1, n -> n + 1).limit(pagesLimit).toList();

        return list.size() != 1 ? list : null;
    }
}
