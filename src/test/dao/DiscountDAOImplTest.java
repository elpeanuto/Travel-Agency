package dao;

import edu.elpeanuto.tms.model.Discount;
import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.servies.dao.DiscountDAO;
import edu.elpeanuto.tms.servies.dao.MessagesDAO;
import edu.elpeanuto.tms.servies.dao.daoImpl.DiscountDAOImpl;
import edu.elpeanuto.tms.servies.dao.daoImpl.MessagesDAOImpl;
import edu.elpeanuto.tms.servies.dao.daoImpl.UserDAOImpl;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.TestConnection;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountDAOImplTest {
    private static DiscountDAO discountDAO;
    private static DBConnection dbConnection;

    @BeforeAll
    public static void startUp(){
        dbConnection = new TestConnection();
        discountDAO = new DiscountDAOImpl(dbConnection);
    }
    @BeforeEach
    public void setUp() throws DAOException {
        try (Connection con = dbConnection.getConnection();
             Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate("DROP TABLE IF EXISTS discount");
            stmt.executeUpdate("CREATE TABLE discount" +
                    "(" +
                    "    id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "    step INTEGER                        NOT NULL," +
                    "    max  INTEGER                        NOT NULL" +
                    ");");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Test
    public void test1() throws DAOException, SQLException, NoEntityException {
        discountDAO.save(new Discount(1L, 10, 20));

        assertEquals(20, discountDAO.get(1L).orElseThrow(NoEntityException::new).getMax());
    }

    @Test
    public void test2() throws DAOException, SQLException, NoEntityException {
        discountDAO.save(new Discount(1L, 10, 20));
        discountDAO.update(new Discount(1L, 10, 30));

        assertEquals(30, discountDAO.get(1L).orElseThrow(NoEntityException::new).getMax());
    }
}
