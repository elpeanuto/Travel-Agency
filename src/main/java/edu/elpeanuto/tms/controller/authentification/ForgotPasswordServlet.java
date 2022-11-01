package edu.elpeanuto.tms.controller.authentification;

import edu.elpeanuto.tms.servies.dao.UserDAO;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller which send generated OTP to clients email
 */
@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    private Logger logger;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();

        logger = (Logger) sc.getAttribute("logger");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/authentication/forgotPassword.jsp").include(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        HttpSession mySession = req.getSession();

        String otpValue = generateOtp();

        // Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("travelbookmanagesystem@gmail.com", "vypjnduzbhdlpbui");
            }
        });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Password recovery");
            message.setText("Your OTP is: " + otpValue);
            // send message
            Transport.send(message);

        } catch (MessagingException e) {
            logger.error("Fatal error: Failed to compose message");
            throw new RuntimeException(e);
        }

        mySession.setAttribute("otp", otpValue);
        mySession.setAttribute("email", email);

        resp.sendRedirect("validateOtp");
    }

    private String generateOtp() {
        StringBuilder otp = new StringBuilder("");

        int otpLength = 6;
        for (int i = 0; i < otpLength; i++)
            otp.append((int) (Math.random() * 10));

        return otp.toString();
    }
}
