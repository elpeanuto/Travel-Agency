package edu.elpeanuto.tms.controller.admin;

import edu.elpeanuto.tms.model.Message;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * Output controller for received unprocessed messages
 */
@WebServlet("/allMessages")
public class AllMessagesServlet extends HttpServlet {
    private Logger logger;
    MessagesDAO messagesDAO;

    private Integer numOfStringOnPage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        messagesDAO = (MessagesDAO) sc.getAttribute("messagesDAO");
        logger = (Logger) sc.getAttribute("logger");

        numOfStringOnPage = 4;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("messageList", pagination(req, numOfStringOnPage));
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoEntityException e) {
            logger.warn(e.getMessage());
        }

        req.getRequestDispatcher("view/admin/allMessages.jsp").include(req, resp);
    }

    private List<Message> pagination(HttpServletRequest request, Integer numOfStrings) throws DAOException, NoEntityException {
        int page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("positionList", pages(numOfStrings));

        return messagesDAO.getPaginationNotAnswered(page * numOfStrings - numOfStrings, numOfStrings);
    }

    private List<Integer> pages(Integer numOfStrings) throws DAOException, NoEntityException {
        int pagesLimit = (int) Math.ceil(messagesDAO.getNumberOfNotesNotAnswered().orElseThrow(NoEntityException::new) / (double) numOfStrings);
        List<Integer> list = Stream.iterate(1, n -> n + 1).limit(pagesLimit).toList();

        return list.size() != 1 ? list : null;
    }
}
