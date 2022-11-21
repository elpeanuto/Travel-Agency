package dao;

import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.model.enums.UserStatus;
import edu.elpeanuto.tms.servies.dao.UserDAO;
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


public class UserDAOImplTest {
    private static UserDAO userDAO;
    private static DBConnection dbConnection;

    @BeforeAll
    public static void startUp(){
        dbConnection = new TestConnection();
        userDAO = new UserDAOImpl(dbConnection);
    }

    @BeforeEach
    public void setUp() throws  DAOException {
        try (Connection con = dbConnection.getConnection();
             Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.executeUpdate("DROP TABLE IF EXISTS user_info");
            stmt.executeUpdate("CREATE TABLE users" +
                    "(" +
                    "    id            INT PRIMARY KEY AUTO_INCREMENT," +
                    "    user_info_id  INT REFERENCES user_info (id)," +
                    "    name          VARCHAR(20) NOT NULL," +
                    "    password      VARCHAR(20) NOT NULL," +
                    "    email         VARCHAR(50) NOT NULL," +
                    "    phone_number  VARCHAR(20) NOT NULL," +
                    "    status        VARCHAR(20) NOT NULL" +
                    ")");
            stmt.executeUpdate("CREATE TABLE user_info" +
                    "(" +
                    "    id                  INT PRIMARY KEY AUTO_INCREMENT," +
                    "    surname             VARCHAR(20)," +
                    "    name                VARCHAR(20)," +
                    "    date_of_birth       DATE," +
                    "    gender              VARCHAR(20)," +
                    "    nationality         VARCHAR(20)," +
                    "    passport_serial     VARCHAR(10)," +
                    "    passport_number     VARCHAR(10)," +
                    "    passport_valid_date DATE" +
                    ");");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


    @Test
    public void test1() throws DAOException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));

        assertEquals(4, userDAO.getAll().size());
    }

    @Test
    public void test2() throws DAOException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));
        userDAO.save(new User(null, "userName2", "userPassword2", "userEmail2", "userPhoneNumber2", UserStatus.Client));

        assertEquals("userName", userDAO.get(1L).orElseThrow(NoEntityException::new).getName());
        assertEquals("userName2", userDAO.get(2L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test3() throws DAOException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));
        userDAO.update(new User(1L,1L, "userName2", "userPassword2", "userEmail2", "userPhoneNumber2", UserStatus.Client));

        assertEquals("userName2", userDAO.get(1L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test4() throws DAOException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));

        assertFalse(userDAO.userVerify("userEmail", "userPassword2"));
        assertTrue(userDAO.userVerify("userEmail", "userPassword"));
    }

    @Test
    public void test5() throws DAOException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));

        assertTrue(userDAO.isEmailOccupied("userEmail"));
        assertFalse(userDAO.isEmailOccupied("userEmail1"));
    }

    @Test
    public void test6() throws DAOException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));

        assertEquals("userName", userDAO.get("userEmail").orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test7() throws DAOException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Banned));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Manager));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Admin));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Leader));

        assertEquals(4, userDAO.getNumberOfNotesByStatus(UserStatus.Leader).orElseThrow(NoEntityException::new));
        assertEquals(3, userDAO.getNumberOfNotesByStatus(UserStatus.Admin).orElseThrow(NoEntityException::new));
        assertEquals(2, userDAO.getNumberOfNotesByStatus(UserStatus.Manager).orElseThrow(NoEntityException::new));
    }

    @Test
    public void test8() throws DAOException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Banned));

        assertTrue(userDAO.userVerify("userEmail", "userPassword"));

        userDAO.resetPassword("userEmail", "newPassword");

        assertTrue(userDAO.userVerify("userEmail", "newPassword"));

    }

    @Test
    public void test9() throws DAOException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));

        userDAO.promote(1L, UserStatus.Manager);

        assertEquals(UserStatus.Manager, userDAO.get("userEmail").orElseThrow(NoEntityException::new).getStatus());
    }

    @Test
    public void test10() throws DAOException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Manager));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Manager));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Manager));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Manager));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", UserStatus.Client));

        assertEquals(3, userDAO.getPaginationByStatus(0, 3, UserStatus.Admin).size());
        assertEquals(2, userDAO.getPaginationByStatus(3, 3, UserStatus.Admin).size());

        assertEquals(1, userDAO.getPaginationByStatus(0, 3, UserStatus.Manager).size());
    }
}
