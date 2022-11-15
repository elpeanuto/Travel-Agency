package edu.elpeanuto.tms.controller.user;

import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.servies.dao.MessagesDAO;
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
import java.util.List;
import java.util.stream.Stream;

/**
 * Clients output message controller
 */
@WebServlet("/myMessages")
public class MyMessagesServlet extends HttpServlet {
    private Logger logger;

    HttpSession session;
    MessagesDAO messagesDAO;

    private Integer numOfStringOnPage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        messagesDAO = (MessagesDAO) sc.getAttribute("messagesDAO");
        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage = 2;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();

        try {
            UserDTO userDto = (UserDTO) session.getAttribute("user");
            req.setAttribute("messageList", pagination(req, numOfStringOnPage, userDto.getId()));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("view/user/myMessages.jsp").include(req, resp);
    }

    /**
     * Get data by pieces from db and set pageList.
     * @param request Http servlet request.
     * @param numOfStrings Number of string on one page.
     * @param userId User id.
     * @return List of messages.
     * @throws DAOException Exception: {@link edu.elpeanuto.tms.servies.exception.DAOException check}
     * @throws NoEntityException Exception: {@link edu.elpeanuto.tms.servies.exception.NoEntityException check}
     */
    private List<Message> pagination(HttpServletRequest request, Integer numOfStrings, Long userId) throws DAOException, NoEntityException {
        int page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("positionList", pages(numOfStrings, userId));

        return messagesDAO.getPaginationByUserId(page * numOfStrings - numOfStrings, numOfStrings, userId);
    }

    /**
     * Create page list.
     * @param numOfStrings Number of string on one page.
     * @param userId User id.
     * @return List of pages.
     * @throws DAOException Exception: {@link edu.elpeanuto.tms.servies.exception.DAOException check}
     * @throws NoEntityException Exception: {@link edu.elpeanuto.tms.servies.exception.NoEntityException check}
     */
    private List<Integer> pages(Integer numOfStrings, Long userId) throws DAOException, NoEntityException {
        int pagesLimit = (int) Math.ceil(messagesDAO.getNumberOfNotesByUserId(userId).orElseThrow(NoEntityException::new) / (double) numOfStrings);
        List<Integer> list = Stream.iterate(1, n -> n + 1).limit(pagesLimit).toList();

        return list.size() != 1 ? list : null;
    }
}
