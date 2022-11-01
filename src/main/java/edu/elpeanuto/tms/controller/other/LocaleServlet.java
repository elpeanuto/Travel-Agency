package edu.elpeanuto.tms.controller.other;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Language locale controller
 */
@WebServlet("/setLocale")
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("locale", req.getParameter("locale"));

        String ref = req.getHeader("referer");

        if (ref == null || ref.isEmpty()) {
            ref = "allProduct?page=1";
        }

        resp.sendRedirect(ref);
    }
}
