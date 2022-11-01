package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
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
@WebServlet("/allProductEdit")
public class AllProductEditServlet extends HttpServlet {
    private Logger logger;
    private ProductDAO productDAO;

    private Integer numOfStringOnPage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        productDAO = (ProductDAO) sc.getAttribute("productDAO");
        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage = 3;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("productList", pagination(req, numOfStringOnPage));
        } catch (DAOException e) {
            logger.error(e.getMessage());
        } catch (NoEntityException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("view/admin/allProductEdit.jsp").include(req,resp);
    }

    private List<Product> pagination(HttpServletRequest request, Integer numOfStrings) throws DAOException, NoEntityException {
        int page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("positionList", pages(numOfStrings));

        return productDAO.getPagination(page * numOfStrings - numOfStrings, numOfStrings);
    }

    private List<Integer> pages(Integer numOfStrings) throws DAOException, NoEntityException {
        int pagesLimit = (int) Math.ceil(productDAO.getNumberOfNotes().orElseThrow(NoEntityException::new) / (double) numOfStrings);
        List<Integer> list = Stream.iterate(1, n -> n + 1).limit(pagesLimit).toList();

        return list.size() != 1 ? list : null;
    }
}
