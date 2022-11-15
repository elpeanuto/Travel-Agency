package edu.elpeanuto.tms.controller.user;


import edu.elpeanuto.tms.model.Discount;
import edu.elpeanuto.tms.model.Order;
import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.model.enums.Gender;
import edu.elpeanuto.tms.model.enums.OrderStatus;
import edu.elpeanuto.tms.servies.dao.DiscountDAO;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.dao.UserDAO;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Processing order controller.
 */
@WebServlet("/makeOrder")
public class MakeOrderServlet extends HttpServlet {
    private Logger logger;

    /**
     * DiscountNumbers contains total price after discount and discount.
     */
    private static class DiscountNumbers {
        float total;
        int totalDiscount;

        public DiscountNumbers(float total, int totalDiscount) {
            this.total = total;
            this.totalDiscount = totalDiscount;
        }

        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

        public int getTotalDiscount() {
            return totalDiscount;
        }

        public void setTotalDiscount(int totalDiscount) {
            this.totalDiscount = totalDiscount;
        }
    }

    private UserDAO userDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private DiscountDAO discountDAO;
    HttpSession session;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        userDAO = (UserDAO) sc.getAttribute("userDAO");
        productDAO = (ProductDAO) sc.getAttribute("productDAO");
        orderDAO = (OrderDAO) sc.getAttribute("orderDAO");
        discountDAO = (DiscountDAO) sc.getAttribute("discountDAO");

        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();

        UserDTO userDto = (UserDTO) session.getAttribute("user");
        long id = Long.parseLong(req.getParameter("id"));

        Product product;

        try {
            product = productDAO.get(id).orElseThrow(NoEntityException::new);
            req.setAttribute("product", product);
            req.setAttribute("user", userDAO.get(userDto.getId()).orElseThrow(NoEntityException::new));
        } catch (DAOException e) {
            logger.error(e.getMessage());
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }

        req.getRequestDispatcher("view/user/makeOrder.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");
        String realName = req.getParameter("realName");
        String realSurName = req.getParameter("realSurName");
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        String dateOfBirth = req.getParameter("dateOfBirth");
        String citizenship = req.getParameter("citizenship");
        String passportSerial = req.getParameter("passportSerial");
        String passportNumber = req.getParameter("passportNumber");
        String passportValidDate = req.getParameter("passportValidDate");


        session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        long productId = Long.parseLong(req.getParameter("id"));
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        float totalPrice;

        try {
            totalPrice = countTotal(productId, user.getId()).getTotal();
            session.setAttribute("discount", countTotal(productId, user.getId()).getTotalDiscount());

            Order order = new Order(null, user.getId(), productId, OrderStatus.Registered, date, name, phoneNumber, email, realName,
                    realSurName, gender, dateOfBirth, citizenship, passportSerial, passportNumber, passportValidDate, (int) totalPrice);

            orderDAO.save(order);

            resp.sendRedirect("bill?id=" + order.getId());
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }
    }

    /**
     * Count discount
     * @param productId Product id.
     * @param userId User id.
     * @return New DiscountNumbers
     * @throws DAOException Exception: {@link edu.elpeanuto.tms.servies.exception.DAOException check}
     * @throws NoEntityException Exception: {@link edu.elpeanuto.tms.servies.exception.NoEntityException check}
     */
    private DiscountNumbers countTotal(long productId, long userId) throws DAOException, NoEntityException {
        float total;

        Integer productPrice = productDAO.get(productId).orElseThrow(NoEntityException::new).getPrice();
        Discount discount = discountDAO.get(1L).orElseThrow(NoEntityException::new);
        Integer totalDiscount = orderDAO.numOfRegisteredOrders(userId).orElseThrow(NoEntityException::new) * discount.getStep();


        if (totalDiscount > discount.getMax()) {
            totalDiscount = discount.getMax();
        }

        total = (float) (productPrice - (productPrice * (totalDiscount / 100.0)));

        return new DiscountNumbers(total, totalDiscount);
    }
}

