package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.servies.dao.MessagesDAO;
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
import java.util.Date;

/**
 * Message send controller
 */
@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    private Logger logger;

    HttpSession session;
    UserDAO userDAO;
    MessagesDAO messagesDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        userDAO = (UserDAO) sc.getAttribute("userDAO");
        messagesDAO =(MessagesDAO) sc.getAttribute("messagesDAO");

        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();

        try {
            UserDTO userDto = (UserDTO) session.getAttribute("user");
            req.setAttribute("user", userDAO.get(userDto.getId()).orElseThrow(NoEntityException::new));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }

        req.getRequestDispatcher("view/user/contact.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String message = req.getParameter("message");
        String category = req.getParameter("category");

        UserDTO userDto = (UserDTO) session.getAttribute("user");

        try {
            messagesDAO.save(new Message(null, null, userDto.getId(), email, name, category, message, (new Date()).toString()));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        resp.sendRedirect("myMessages?page=1");
    }
}
