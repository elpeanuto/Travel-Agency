package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.servies.alert.SetAlertToRequest;
import edu.elpeanuto.tms.servies.dao.MessagesDAO;
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

/**
 * Message view controller.
 */
@WebServlet("/viewMessage")
public class ViewMessageServlet extends HttpServlet {
    private Logger logger;
    private MessagesDAO messagesDAO;

    @Override
    public void init(ServletConfig config) {
        ServletContext sc = config.getServletContext();

        messagesDAO = (MessagesDAO) sc.getAttribute("messagesDAO");
        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long messageId = Long.valueOf(req.getParameter("id"));

        try {
            System.out.println(messagesDAO.get(messageId).orElseThrow(NoEntityException::new));
            req.setAttribute("message", messagesDAO.get(messageId).orElseThrow(NoEntityException::new));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);

            resp.sendRedirect("allProduct?page=1");
            return;
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
            SetAlertToRequest.setErrorAlert(req);
        }

        req.getRequestDispatcher("view/user/viewMessage.jsp").include(req, resp);
    }
}
