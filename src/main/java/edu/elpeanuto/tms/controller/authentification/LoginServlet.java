package edu.elpeanuto.tms.controller.authentification;

import edu.elpeanuto.tms.model.enums.UserStatus;
import edu.elpeanuto.tms.servies.alert.AlertType;
import edu.elpeanuto.tms.servies.alert.SetAlertToRequest;
import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.PasswordHashing;
import edu.elpeanuto.tms.servies.dto.UserDTO;
import edu.elpeanuto.tms.model.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Client log in controller.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Logger logger;
    private String ref;
    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        userDAO = (UserDAO) sc.getAttribute("userDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ref = req.getHeader("referer");

        req.getRequestDispatcher("view/authentication/login.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        req.setAttribute("pageStatus", "error");

        try {
            if (!userDAO.userVerify(email, PasswordHashing.hashPassword(password))) {
                SetAlertToRequest.setCustomAlert(req, "Error", "Wrong email or password.", AlertType.ERROR);
                resp.sendRedirect("login");

                return;
            }

            User user = userDAO.get(email).orElseThrow(FailToUpdateDBException::new);
            UserDTO userDto = new UserDTO(user.getId(), user.getUserInfoId(), user.getName(), user.getEmail(), user.getStatus());

            if (userDto.getStatus().equals(UserStatus.Banned)) {
                SetAlertToRequest.setCustomAlert(req, "Error", "This account has been blocked.", AlertType.ERROR);
                resp.sendRedirect("login");
                return;
            }

            session.setAttribute("user", userDto);

            if (userDto.getStatus().equals(UserStatus.Leader) || userDto.getStatus().equals(UserStatus.Admin) || userDto.getStatus().equals(UserStatus.Manager)) {
                resp.sendRedirect("adminHome");
                return;
            }

            if(ref != null && !ref.isEmpty() && ref.contains("viewProduct")){
                resp.sendRedirect(ref);
                return;
            }

            resp.sendRedirect("allProduct?page=1");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);

            resp.sendRedirect("login");
        } catch (FailToUpdateDBException e) {
            logger.warn(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);

            resp.sendRedirect("login");
        }
    }

}
