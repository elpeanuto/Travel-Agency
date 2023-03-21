package mockitoTests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import edu.elpeanuto.tms.servies.dao.daoImpl.DiscountDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.elpeanuto.tms.model.Discount;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.exception.DAOException;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiscountDAOImplTest {
    @Mock
    private DBConnection dbConnectionMock;
    @Mock
    private Connection connectionMock;
    @Mock
    private PreparedStatement statementMock;
    @Mock
    private ResultSet resultSetMock;

    private DiscountDAOImpl discountDAO;

    @Before
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);

        discountDAO = new DiscountDAOImpl(dbConnectionMock);

        when(dbConnectionMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);
        when(statementMock.executeUpdate()).thenReturn(1);
    }

    @Test(expected = DAOException.class)
    public void testGetByIdSQLException() throws SQLException, DAOException {
        when(statementMock.executeQuery()).thenThrow(new SQLException());

        discountDAO.get(1L);
    }

    @Test(expected = DAOException.class)
    public void testGetAllSQLException() throws SQLException, DAOException {
        when(statementMock.executeQuery()).thenThrow(new SQLException());

        discountDAO.getAll();
    }

    @Test(expected = DAOException.class)
    public void testSaveSQLException() throws SQLException, DAOException {
        when(statementMock.executeUpdate()).thenThrow(new SQLException());

        discountDAO.save(new Discount(1L, 5, 10));
    }
    @Test(expected = DAOException.class)
    public void testUpdateSQLException() throws SQLException, DAOException {
        when(statementMock.executeUpdate()).thenThrow(new SQLException());

        discountDAO.update(new Discount(1L, 5, 10));
    }

    @Test
    public void testGetById() throws SQLException, DAOException {
        Long id = 1L;
        Integer step = 5;
        Integer max = 10;

        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("step")).thenReturn(step);
        when(resultSetMock.getInt("max")).thenReturn(max);

        Optional<Discount> result = discountDAO.get(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals(step, result.get().getStep());
        assertEquals(max, result.get().getMax());

        verify(connectionMock).prepareStatement("SELECT * FROM discount WHERE id = ?");
        verify(statementMock).setLong(1, id);
        verify(statementMock).executeQuery();
    }

    @Test
    public void testGetAll() throws SQLException, DAOException {
        Integer step1 = 5;
        Integer max1 = 10;
        Integer step2 = 10;
        Integer max2 = 20;

        when(resultSetMock.next()).thenReturn(true, true, false);
        when(resultSetMock.getInt("step")).thenReturn(step1, step2);
        when(resultSetMock.getInt("max")).thenReturn(max1, max2);

        List<Discount> result = discountDAO.getAll();

        assertEquals(2, result.size());

        Discount discount1 = result.get(0);
        assertEquals(step1, discount1.getStep());
        assertEquals(max1, discount1.getMax());

        Discount discount2 = result.get(1);
        assertEquals(step2, discount2.getStep());
        assertEquals(max2, discount2.getMax());

        verify(connectionMock).prepareStatement("SELECT * FROM discount");
        verify(statementMock).executeQuery();
    }

    @Test
    public void testSave() throws SQLException, DAOException {
        Discount discount = new Discount(null, 5, 10);

        boolean result = discountDAO.save(discount);

        assertTrue(result);

        verify(connectionMock).prepareStatement("INSERT INTO discount(step, max) values(?, ?)");
        verify(statementMock).setInt(1, 5);
        verify(statementMock).setInt(2, 10);
        verify(statementMock).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException, DAOException {
        Discount discount = new Discount(2L, 5, 10);

        boolean result = discountDAO.update(discount);

        assertTrue(result);

        verify(connectionMock).prepareStatement("UPDATE discount SET step=?, max=? WHERE id=?");
        verify(statementMock).setInt(1, discount.getStep());
        verify(statementMock).setInt(2, discount.getMax());
        verify(statementMock).setLong(3, discount.getId());
        verify(statementMock).executeUpdate();
    }
}