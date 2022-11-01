package edu.elpeanuto.tms.controller.authentification;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller which compare user OTP with generated one
 */
@WebServlet("/validateOtp")
public class ValidateOtpServlet extends HttpServlet {
    HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/authentication/enterOtp.jsp").include(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();

        String userOtp = req.getParameter("otp");
        String correctOtp = (String) session.getAttribute("otp");

        if (userOtp != null && correctOtp != null && userOtp.matches("^\\d{6}$") && correctOtp.equals(userOtp)) {
            resp.sendRedirect("newPassword");
        } else {
            resp.sendRedirect("validateOtp");
        }
    }
}
