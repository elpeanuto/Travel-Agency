package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.FailToUpdateDBException;
import edu.elpeanuto.tms.servies.exception.EnumParseException;
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
 * Tour update controller
 */
@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {
    private Logger logger;

    private ProductDAO productDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        productDAO = (ProductDAO) sc.getAttribute("productDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        try {
            Product product = getProductFromRequest(req);
            product.setId(id);
            if(!productDAO.update(product))
                throw new FailToUpdateDBException();
        } catch (DAOException | EnumParseException e) {
            logger.error(e.getMessage());
        } catch (FailToUpdateDBException e) {
            logger.warn(e.getMessage());
        }

        resp.sendRedirect("allProductEdit?page=1");
    }

    private Product getProductFromRequest(HttpServletRequest req) throws EnumParseException {
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String type = req.getParameter("type");
        Integer price = Integer.parseInt(req.getParameter("price"));
        String hotelName = req.getParameter("hotelName");
        String hotelType = req.getParameter("hotelType");
        String description = req.getParameter("description");
        Integer numberOfTourists = Integer.parseInt(req.getParameter("numberOfTourists"));
        String arrivalDate = req.getParameter("arrivalDate");
        String arrivalPlace = req.getParameter("arrivalPlace");
        String departureDate = req.getParameter("departureDate");
        String departurePlace = req.getParameter("departurePlace");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String foodInPrice = req.getParameter("foodInPrice");
        String flightInPrice = req.getParameter("flightInPrice");
        Integer amountOfDays = Integer.parseInt(req.getParameter("amountOfDays"));
        String active = req.getParameter("active");

        return  new Product(null, name, description, category, type, price, active,
                hotelName, hotelType, arrivalDate, departureDate, arrivalPlace, departurePlace,
                country, city, foodInPrice, flightInPrice, amountOfDays, numberOfTourists);
    }
}
