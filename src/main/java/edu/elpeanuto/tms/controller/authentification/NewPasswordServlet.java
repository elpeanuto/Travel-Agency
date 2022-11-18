package edu.elpeanuto.tms.controller.authentification;

import edu.elpeanuto.tms.servies.alert.AlertType;
import edu.elpeanuto.tms.servies.alert.SetAlertToRequest;
import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.PasswordHashing;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.FailToUpdateDBException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.slf4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller which sets new password for client.
 */
@WebServlet("/newPassword")
public class NewPasswordServlet extends HttpServlet {
    private Logger logger;
    private UserDAO userDAO;
    HttpSession session;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        userDAO = (UserDAO) sc.getAttribute("userDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/authentication/newPassword.jsp").include(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();

        String newPassword = req.getParameter("password");
        String newPasswordRepetition = req.getParameter("re-password");

        newPassword = PasswordHashing.hashPassword(newPassword);
        newPasswordRepetition = PasswordHashing.hashPassword(newPasswordRepetition);

        if (!newPassword.equals(newPasswordRepetition)) {
            SetAlertToRequest.setCustomAlert(req, "Error", "Passwords do not match.", AlertType.ERROR);
            resp.sendRedirect("newPassword");
            return;
        }

        try {
            if (!userDAO.resetPassword((String) session.getAttribute("email"), newPasswordRepetition))
                throw new FailToUpdateDBException();

            SetAlertToRequest.setSuccessAlert(req);
            resp.sendRedirect("login");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);

            resp.sendRedirect("newPassword");
        } catch (FailToUpdateDBException e) {
            logger.warn(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);

            resp.sendRedirect("newPassword");
        }
    }
}
