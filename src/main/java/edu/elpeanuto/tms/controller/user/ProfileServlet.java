package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.servies.alert.SetAlertToRequest;
import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.dto.UserDTO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.slf4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/**
 * Clients output profile controller.
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private Logger logger;
    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) {
        ServletContext sc = config.getServletContext();

        userDAO = (UserDAO) sc.getAttribute("userDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDto = (UserDTO) req.getSession().getAttribute("user");

        try {
            req.setAttribute("user", userDAO.get(userDto.getId()).orElseThrow(NoEntityException::new));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);

            resp.sendRedirect("allProduct?page=1");
            return;
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);
        }

        req.getRequestDispatcher("view/user/profile.jsp").include(req, resp);

    }
}
