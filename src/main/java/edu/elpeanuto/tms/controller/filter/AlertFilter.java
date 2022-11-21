package edu.elpeanuto.tms.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AlertFilter implements Filter {
    private int alertCounter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        alertCounter = 0;
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);

        Object object = session.getAttribute("alertFlag");
        Boolean alertFlag = (Boolean) (object == null ? false : object);

        if (alertFlag) {
            alertCounter++;
        }

        if(alertCounter == 2){
            session.setAttribute("alertFlag", false);
            alertCounter = 0;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
