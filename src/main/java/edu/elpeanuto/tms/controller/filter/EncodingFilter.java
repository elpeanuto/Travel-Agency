package edu.elpeanuto.tms.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter that set request and response encoding to UTF-8.
 */
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (null == servletRequest.getCharacterEncoding())
            servletRequest.setCharacterEncoding("UTF-8");

        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
