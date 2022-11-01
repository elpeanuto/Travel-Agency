package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.model.enums.ProductType;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.dto.ProductFilterDTO;
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
import java.util.List;
import java.util.stream.Stream;

/**
 * Output tour controller
 */
@WebServlet("/allProduct")
public class AllProductServlet extends HttpServlet {
    private Logger logger;
    private Integer numOfStringOnPage;

    private ProductDAO productDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        productDAO = (ProductDAO) sc.getAttribute("productDAO");
        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage = 12;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");

        if (user != null)
            req.setAttribute("status", User.STATUS.Client.name());

        try {
            ProductFilterDTO filter = getProductFilterFromRequest(req);
            req.setAttribute("filter", filter);

            paginationWithFilter(req, filter, numOfStringOnPage, productDAO);

        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }

        req.getRequestDispatcher("view/user/home.jsp").include(req, resp);
    }

    private ProductFilterDTO getProductFilterFromRequest(HttpServletRequest req) throws DAOException, NoEntityException {
        String searchPattern = req.getParameter("search");
        String numberOfTourists = req.getParameter("numberOfTourists");
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        String category = req.getParameter("category");
        String hotelType = req.getParameter("hotelType");

        return new ProductFilterDTO(
                category != null ? category : ProductFilterDTO.CATEGORY.All.name(),
                hotelType != null ? hotelType : ProductFilterDTO.HOTEL_TYPE.All.name(),
                searchPattern != null && !searchPattern.equals("") ? req.getParameter("search") : null,
                numberOfTourists != null && !numberOfTourists.equals("") ? Integer.parseInt(req.getParameter("numberOfTourists")) : null,
                minPrice != null && !minPrice.equals("") ? Integer.parseInt(req.getParameter("minPrice")) : productDAO.minPrice().orElseThrow(NoEntityException::new),
                maxPrice != null && !maxPrice.equals("") ? Integer.parseInt(req.getParameter("maxPrice")) : productDAO.maxPrice().orElseThrow(NoEntityException::new));
    }

    private void paginationWithFilter(HttpServletRequest request, ProductFilterDTO filter, Integer numOfStrings, ProductDAO dao) throws DAOException, NoEntityException {
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int numOfNotes = dao.getNumberOfNotes(filter).orElseThrow(NoEntityException::new);
        int numOfPages = (int) Math.ceil(numOfNotes / (double) numOfStrings);

        List<Integer> pagesList = Stream.iterate(1, n -> n + 1).limit(numOfPages).toList();

        List<Product> listHotProducts = dao.search(filter, pageNum * numOfStrings - numOfStrings, numOfStrings, "Hot");

        request.setAttribute("currentPosition", 1);
        request.setAttribute("positionList", pagesList.size() != 1 ? pagesList : null);

        if (listHotProducts.size() == numOfStrings) {
            request.setAttribute("productList", listHotProducts);
        } else if (listHotProducts.size() != 0) {
            int numOfMissingLines = numOfStrings - listHotProducts.size();

            listHotProducts.addAll(dao.search(filter, 0, numOfMissingLines, ProductType.Ordinary.name()));

            request.setAttribute("productList", listHotProducts);
        } else {
            filter.setType(ProductType.Hot.name());
            int numOfHotPages = (int) Math.ceil(dao.getNumberOfNotes(filter).orElseThrow(NoEntityException::new) / (double) numOfStrings);
            int numOfHotNotes = dao.getNumberOfNotes(filter).orElseThrow(NoEntityException::new);

            int pageOrd = pageNum - numOfHotPages;
            int start;
            if (numOfHotNotes % numOfStrings != 0)
                start = pageOrd * numOfStrings - numOfStrings + numOfStrings - (numOfHotNotes % numOfStrings);
            else
                start = pageOrd * numOfStrings - numOfStrings;

            request.setAttribute("productList", dao.search(filter, start, numOfStrings, ProductType.Ordinary.name()));
        }
    }
}
