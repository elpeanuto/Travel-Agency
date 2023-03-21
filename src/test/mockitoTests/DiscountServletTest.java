package mockitoTests;

import edu.elpeanuto.tms.controller.admin.DiscountServlet;
import edu.elpeanuto.tms.model.Discount;
import edu.elpeanuto.tms.servies.dao.DiscountDAO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServletTest {
    @Mock
    private DiscountDAO discountDAOMock;
    @Mock
    private HttpServletRequest reqMock;
    @Mock
    private HttpServletResponse respMock;
    @Mock
    private ServletConfig servletConfigMock;
    @Mock
    private ServletContext servletContextMock;
    @Mock
    private Logger loggerMock;
    @Mock
    private RequestDispatcher dispatcherMock;
    @Mock
    private HttpSession sessionMock;

    private DiscountServletWrapper discountWrapper;

    private class DiscountServletWrapper extends DiscountServlet {
        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doGet(req, resp);
        }

        @Override
        public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            super.doPost(req, resp);
        }
    }

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        when(servletConfigMock.getServletContext()).thenReturn(servletContextMock);
        when(servletContextMock.getAttribute("discountDAO")).thenReturn(discountDAOMock);
        when(servletContextMock.getAttribute("logger")).thenReturn(loggerMock);

        discountWrapper = new DiscountServletWrapper();
        discountWrapper.init(servletConfigMock);
    }

    @Test
    public void testDoGetCorrectCase() throws DAOException, ServletException, IOException {
        Discount discount = new Discount(1L, 5, 10);

        when(discountDAOMock.get(any(Long.class))).thenReturn(Optional.of(discount));
        when(reqMock.getRequestDispatcher("view/admin/discount.jsp")).thenReturn(dispatcherMock);

        discountWrapper.doGet(reqMock, respMock);

        verify(reqMock).setAttribute("discount", discount);
        verify(reqMock).getRequestDispatcher("view/admin/discount.jsp");
        verify(dispatcherMock).include(reqMock, respMock);
    }

    @Test
    public void testDoGetDAOExceptionCase() throws DAOException, ServletException, IOException {
        Exception e = new DAOException();

        when(discountDAOMock.get(any(Long.class))).thenThrow(e);
        when(reqMock.getRequestDispatcher("view/admin/discount.jsp")).thenReturn(dispatcherMock);
        when(reqMock.getSession()).thenReturn(sessionMock);

        discountWrapper.doGet(reqMock, respMock);

        verify(loggerMock).error(e.getMessage());
        verify(sessionMock).setAttribute("alertHeader", "Error");
        verify(dispatcherMock).include(reqMock, respMock);
    }

    @Test
    public void testDoPostCorrectCase() throws DAOException, IOException {
        when(reqMock.getParameter("step")).thenReturn("5");
        when(reqMock.getParameter("max")).thenReturn("10");
        when(reqMock.getSession()).thenReturn(sessionMock);
        when(discountDAOMock.update(any(Discount.class))).thenReturn(true);

        discountWrapper.doPost(reqMock, respMock);

        verify(sessionMock).setAttribute("alertHeader", "Success");
        verify(respMock).sendRedirect("discount");
    }

    @Test
    public void testDoPostIncorrectParamsCase() throws IOException {
        when(reqMock.getParameter("step")).thenReturn("10");
        when(reqMock.getParameter("max")).thenReturn("5");
        when(reqMock.getSession()).thenReturn(sessionMock);

        discountWrapper.doPost(reqMock, respMock);

        verify(sessionMock).setAttribute("alertHeader", "Error");
        verify(sessionMock).setAttribute("alertBody", "Max must be greater than step.");
        verify(respMock).sendRedirect("discount");
    }

    @Test
    public void testDoPostDAOExceptionCase() throws DAOException, IOException {
        Exception e = new DAOException();

        when(reqMock.getParameter("step")).thenReturn("5");
        when(reqMock.getParameter("max")).thenReturn("10");
        when(reqMock.getSession()).thenReturn(sessionMock);
        when(discountDAOMock.update(any(Discount.class))).thenThrow(e);

        discountWrapper.doPost(reqMock, respMock);

        verify(loggerMock).error(e.getMessage());
        verify(sessionMock).setAttribute("alertHeader", "Error");
        verify(respMock).sendRedirect("adminHome");
    }

    @Test
    public void testDoPostFailedToUpdateExceptionCase() throws DAOException, IOException {
        when(reqMock.getParameter("step")).thenReturn("5");
        when(reqMock.getParameter("max")).thenReturn("10");
        when(reqMock.getSession()).thenReturn(sessionMock);
        when(discountDAOMock.update(any(Discount.class))).thenReturn(false);

        discountWrapper.doPost(reqMock, respMock);

        verify(sessionMock).setAttribute("alertHeader", "Warning");
        verify(respMock).sendRedirect("discount");
    }
}
