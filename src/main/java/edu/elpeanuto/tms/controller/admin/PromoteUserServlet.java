package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.dto.UserDTO;
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
 * User status change controller
 */
@WebServlet("/promoteUser")
public class PromoteUserServlet extends HttpServlet {
    private Logger logger;
    private UserDAO userDAO;

    private Integer numOfStringOnPage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        userDAO = (UserDAO) sc.getAttribute("userDAO");
        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage = 3;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDTO user = (UserDTO) req.getSession().getAttribute("user");

            req.setAttribute("userList", pagination(req, numOfStringOnPage, user.getStatus()));

        } catch (DAOException e) {
            logger.error(e.getMessage());
        } catch (NoEntityException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("view/admin/promoteUser.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        try {
            if (!userDAO.promote(id, User.STATUS.valueOf(req.getParameter("status")).name()))
                throw new FailToUpdateDBException();
        } catch (DAOException e) {
            logger.error(e.getMessage());
        } catch (FailToUpdateDBException e) {
            logger.warn(e.getMessage());
        }

        resp.sendRedirect("promoteUser");
    }

    private List<User> pagination(HttpServletRequest request, Integer numOfStrings, String status) throws DAOException, NoEntityException {
        int page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("positionList", pages(numOfStrings, status));

        return userDAO.getPaginationByStatus(page * numOfStrings - numOfStrings, numOfStrings, status);
    }

    private List<Integer> pages(Integer numOfStrings, String status) throws DAOException, NoEntityException {
        int pagesLimit = (int) Math.ceil(userDAO.getNumberOfNotesByStatus(status).orElseThrow(NoEntityException::new) / (double) numOfStrings);
        List<Integer> list = Stream.iterate(1, n -> n + 1).limit(pagesLimit).toList();

        return list.size() != 1 ? list : null;
    }
}
