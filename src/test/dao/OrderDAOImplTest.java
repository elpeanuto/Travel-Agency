package dao;

import edu.elpeanuto.tms.model.Order;
import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.model.enums.OrderStatus;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.dao.daoImpl.OrderDAOImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDAOImplTest {
    private static OrderDAO orderDAO;
    private static DBConnection dbConnection;

    @BeforeAll
    public static void startUp(){
        dbConnection = new TestConnection();
        orderDAO = new OrderDAOImpl(dbConnection);
    }
    @BeforeEach
    public void setUp() throws DAOException {
        try (Connection con = dbConnection.getConnection();
             Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate("DROP TABLE IF EXISTS booking");
            stmt.executeUpdate("CREATE TABLE booking" +
                    "(" +
                    "    id                INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "    userId            INT REFERENCES user (id)," +
                    "    productId         INT REFERENCES product (id)," +
                    "    status            VARCHAR(50)                    NOT NULL," +
                    "    date              VARCHAR(50)                    NOT NULL," +
                    "    name              VARCHAR(50)                    NOT NULL," +
                    "    phoneNumber       VARCHAR(50)                    NOT NULL," +
                    "    email             VARCHAR(50)                    NOT NULL," +
                    "    realName          VARCHAR(50)                    NOT NULL," +
                    "    realSurName       VARCHAR(50)                    NOT NULL," +
                    "    gender            VARCHAR(50)                    NOT NULL," +
                    "    dateOfBirth       VARCHAR(50)                    NOT NULL," +
                    "    citizenship       VARCHAR(50)                    NOT NULL," +
                    "    passportSerial    VARCHAR(50)                    NOT NULL," +
                    "    passportNumber    VARCHAR(50)                    NOT NULL," +
                    "    passportValidDate VARCHAR(50)                    NOT NULL," +
                    "    totalPrice        INTEGER                        NOT NULL" +
                    ");");

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Test
    public void test1() throws DAOException, SQLException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));

        assertEquals(2, orderDAO.getAll().size());
    }

    @Test
    public void test2() throws DAOException, NoEntityException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name2",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));

        assertEquals("name", orderDAO.get(1L).orElseThrow(NoEntityException::new).getName());
        assertEquals("name2", orderDAO.get(2L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test3() throws DAOException, NoEntityException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Canceled.name(), "date", "name2",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name2",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));

        assertEquals(4, orderDAO.getNumberOfNotesByUserId(1L).orElseThrow(NoEntityException::new));
    }

    @Test
    public void test4() throws DAOException, NoEntityException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Canceled.name(), "date", "name2",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name2",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));

        assertEquals(3, orderDAO.numOfRegisteredOrders(1L).orElseThrow(NoEntityException::new));
    }

    @Test
    public void test5() throws DAOException, NoEntityException {
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Registered.name(), "date", "name",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));
        orderDAO.save(new Order(null, 1L, 1L, OrderStatus.Paid.name(), "date", "name2",
                "phoneNumber", "email", "realName", "realSurName", "gender",
                "dateOfBirth", "citizenship", "passportSerial", "passportNumber",
                "passportValidDate", 1000));


        assertTrue(orderDAO.changeStatus(1L, OrderStatus.Canceled.name()));
        assertTrue(orderDAO.changeStatus(2L, OrderStatus.Paid.name()));

        assertEquals(OrderStatus.Canceled.name(), orderDAO.get(1L).orElseThrow(NoEntityException::new).getStatus());
        assertEquals(OrderStatus.Paid.name(), orderDAO.get(2L).orElseThrow(NoEntityException::new).getStatus());

    }

}
