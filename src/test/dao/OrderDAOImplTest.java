package dao;

import edu.elpeanuto.tms.model.Order;
import edu.elpeanuto.tms.model.enums.Gender;
import edu.elpeanuto.tms.model.enums.OrderStatus;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
import edu.elpeanuto.tms.servies.dao.daoImpl.OrderDAOImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDAOImplTest {
    private static OrderDAO orderDAO;
    private static DBConnection dbConnection;

    @BeforeAll
    public static void startUp() {
        dbConnection = new TestConnection();
        orderDAO = new OrderDAOImpl(dbConnection);
    }

    @BeforeEach
    public void setUp() throws DAOException {
        try (Connection con = dbConnection.getConnection();
             Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate("DROP TABLE IF EXISTS orders");
            stmt.executeUpdate("CREATE TABLE orders" +
                    "(" +
                    "    id                  INT PRIMARY KEY AUTO_INCREMENT," +
                    "    user_id             INT REFERENCES users (id)," +
                    "    product_id          INT REFERENCES products (id)," +
                    "    status              VARCHAR(20)           NOT NULL," +
                    "    date                DATE                  NOT NULL," +
                    "    name                VARCHAR(20)           NOT NULL," +
                    "    phone_number        VARCHAR(20)           NOT NULL," +
                    "    email               VARCHAR(50)           NOT NULL," +
                    "    real_name           VARCHAR(20)           NOT NULL," +
                    "    real_surname        VARCHAR(20)           NOT NULL," +
                    "    gender              VARCHAR(20)           NOT NULL," +
                    "    date_of_birth       DATE                  NOT NULL," +
                    "    nationality         VARCHAR(20)           NOT NULL," +
                    "    passport_serial     VARCHAR(10)           NOT NULL," +
                    "    passport_number     VARCHAR(10)           NOT NULL," +
                    "    passport_valid_date DATE                  NOT NULL," +
                    "    total_price         INTEGER               NOT NULL" +
                    ");");

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Test
    public void test1() throws DAOException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2022-11-11", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));

        assertEquals(2, orderDAO.getAll().size());
    }

    @Test
    public void test2() throws DAOException, NoEntityException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name2",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));

        assertEquals("name", orderDAO.get(1L).orElseThrow(NoEntityException::new).getName());
        assertEquals("name2", orderDAO.get(2L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test3() throws DAOException, NoEntityException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Canceled, "2022-11-11", "name2",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name2",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));

        assertEquals(4, orderDAO.getNumberOfNotesByUserId(1L).orElseThrow(NoEntityException::new));
    }

    @Test
    public void test4() throws DAOException, NoEntityException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Canceled, "2022-11-11", "name2",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name2",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));

        assertEquals(3, orderDAO.numOfRegisteredOrders(1L).orElseThrow(NoEntityException::new));
    }

    @Test
    public void test5() throws DAOException, NoEntityException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered, "2022-11-11", "name",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Paid, "2022-11-11", "name2",
                "phoneNumber", "email", "realName", "realSurname", Gender.Male,
                "2000-11-3", "citizenship", "wkfuehdgrh", "tnehfejrhf",
                "2022-11-11", 1000));


        assertTrue(orderDAO.changeStatus(1L, OrderStatus.Canceled));
        assertTrue(orderDAO.changeStatus(2L, OrderStatus.Paid));

        assertEquals(OrderStatus.Canceled, orderDAO.get(1L).orElseThrow(NoEntityException::new).getStatus());
        assertEquals(OrderStatus.Paid, orderDAO.get(2L).orElseThrow(NoEntityException::new).getStatus());

    }

}
