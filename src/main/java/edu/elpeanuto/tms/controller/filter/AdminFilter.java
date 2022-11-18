package edu.elpeanuto.tms.controller.filter;

import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.dto.UserDTO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.slf4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter that checks is admin logged.
 */
public class AdminFilter implements Filter {
    private UserDAO userDAO;

    private Logger logger;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext sc = filterConfig.getServletContext();

        logger = (Logger) sc.getAttribute("logger");
        userDAO = (UserDAO) sc.getAttribute("userDAO");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);

        UserDTO userDTO = null;

        if (session != null)
            userDTO = (UserDTO) session.getAttribute("user");

        if (session == null || userDTO == null) {
            response.sendRedirect("login");
        } else {
            try {
                String sessionUserStatus = userDTO.getStatus().name();
                String dbUserStatus = userDAO.get(userDTO.getId()).orElseThrow(NoEntityException::new).getStatus().name();

                if (!sessionUserStatus.equals(dbUserStatus)) {
                    response.sendRedirect("login");
                    return;
                }

                filterChain.doFilter(servletRequest, servletResponse);
            } catch (DAOException | NoEntityException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
