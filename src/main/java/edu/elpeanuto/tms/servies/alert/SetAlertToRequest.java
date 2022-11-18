package edu.elpeanuto.tms.servies.alert;

import com.mysql.cj.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetAlertToRequest {
    private static final String ALERT_HEADER = "alertHeader";
    private static final String ALERT_BODY = "alertBody";
    private static final String ALERT_TYPE = "alertType";
    private static final String ALERT_FLAG = "alertFlag";

    public static void setCustomAlert(HttpServletRequest req, String header, String body, AlertType type){
        HttpSession session = req.getSession();

        session.setAttribute(ALERT_HEADER, header);
        session.setAttribute(ALERT_BODY, body);
        session.setAttribute(ALERT_TYPE, type.name().toLowerCase());
        session.setAttribute(ALERT_FLAG, true);
    }

    public static void setErrorAlert(HttpServletRequest req){
        HttpSession session = req.getSession();

        session.setAttribute(ALERT_HEADER, "Error");
        session.setAttribute(ALERT_BODY, "An error occurred on the server side, please try again later.");
        session.setAttribute(ALERT_TYPE, AlertType.ERROR.name().toLowerCase());
        session.setAttribute(ALERT_FLAG, true);
    }
    public static void setSuccessAlert(HttpServletRequest req){
        HttpSession session = req.getSession();

        session.setAttribute(ALERT_HEADER, "Success");
        session.setAttribute(ALERT_BODY, "Operation completed successfully.");
        session.setAttribute(ALERT_TYPE, AlertType.SUCCESS.name().toLowerCase());
        session.setAttribute(ALERT_FLAG, true);
    }
}
