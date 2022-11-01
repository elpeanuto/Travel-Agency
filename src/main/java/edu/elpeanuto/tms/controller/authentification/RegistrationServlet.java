package edu.elpeanuto.tms.controller.authentification;

import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.PasswordHashing;
import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Client registration controller
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private Logger logger;
    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        userDAO = (UserDAO) sc.getAttribute("userDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/authentication/registration.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        String userEmail = req.getParameter("email");
        String userPhoneNumber = req.getParameter("contact");
        String userPassword = req.getParameter("password");
        String userPasswordRepetition = req.getParameter("re-password");
        String status;

        if (userEmail.equals("admin@gmail.com"))
            status = User.STATUS.Leader.name();
        else
            status = User.STATUS.Client.name();

        userPassword = PasswordHashing.hashPassword(userPassword);
        userPasswordRepetition = PasswordHashing.hashPassword(userPasswordRepetition);

        User user = new User(null, userName, userPassword, userEmail, userPhoneNumber, status);

        try {
            if (userDAO.isEmailOccupied(userEmail)) {
                resp.sendRedirect("registration");
            } else if (!userPassword.equals(userPasswordRepetition)) {
                resp.sendRedirect("registration");
            } else if (userDAO.save(user)) {
                resp.sendRedirect("login");
            }
        } catch (DAOException e) {
            logger.error(e.getMessage());
        }
    }
}
