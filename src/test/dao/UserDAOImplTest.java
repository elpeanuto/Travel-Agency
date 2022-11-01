package dao;

import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.dao.daoImpl.UserDAOImpl;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.TestConnection;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
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
            stmt.executeUpdate("DROP TABLE IF EXISTS user");
            stmt.executeUpdate("DROP TABLE IF EXISTS userinfo");
            stmt.executeUpdate("CREATE TABLE user" +
                    "(" +
                    "    id          INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "    userInfoId  INT REFERENCES userInfo (id)," +
                    "    name        VARCHAR(50) NOT NULL," +
                    "    password    VARCHAR(50) NOT NULL," +
                    "    email       VARCHAR(50) NOT NULL," +
                    "    phoneNumber VARCHAR(50) NOT NULL," +
                    "    status      VARCHAR(50) NOT NULL," +
                    "    language    VARCHAR(50)" +
                    ")");
            stmt.executeUpdate("CREATE TABLE userInfo" +
                    "(" +
                    "    id                INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "    surname           VARCHAR(50)," +
                    "    name              VARCHAR(50)," +
                    "    dateOfBirth       VARCHAR(50)," +
                    "    gender            VARCHAR(50)," +
                    "    citizenship       VARCHAR(50)," +
                    "    passportSerial    VARCHAR(50)," +
                    "    passportNumber    VARCHAR(50)," +
                    "    passportValidDate VARCHAR(50)" +
                    ");");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


    @Test
    public void test1() throws DAOException, SQLException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));

        assertEquals(4, userDAO.getAll().size());
    }

    @Test
    public void test2() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));
        userDAO.save(new User(null, "userName2", "userPassword2", "userEmail2", "userPhoneNumber2", User.STATUS.Client.name()));

        assertEquals("userName", userDAO.get(1L).orElseThrow(NoEntityException::new).getName());
        assertEquals("userName2", userDAO.get(2L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test3() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));
        userDAO.update(new User(1L,1L, "userName2", "userPassword2", "userEmail2", "userPhoneNumber2", User.STATUS.Client.name()));

        assertEquals("userName2", userDAO.get(1L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test4() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));

        assertFalse(userDAO.userVerify("userEmail", "userPassword2"));
        assertTrue(userDAO.userVerify("userEmail", "userPassword"));
    }

    @Test
    public void test5() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));

        assertTrue(userDAO.isEmailOccupied("userEmail"));
        assertFalse(userDAO.isEmailOccupied("userEmail1"));
    }

    @Test
    public void test6() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));

        assertEquals("userName", userDAO.get("userEmail").orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test7() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Banned.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Manager.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Admin.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Leader.name()));

        assertEquals(4, userDAO.getNumberOfNotesByStatus(User.STATUS.Leader.name()).orElseThrow(NoEntityException::new));
        assertEquals(3, userDAO.getNumberOfNotesByStatus(User.STATUS.Admin.name()).orElseThrow(NoEntityException::new));
        assertEquals(2, userDAO.getNumberOfNotesByStatus(User.STATUS.Manager.name()).orElseThrow(NoEntityException::new));
    }

    @Test
    public void test8() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Banned.name()));

        assertTrue(userDAO.userVerify("userEmail", "userPassword"));

        userDAO.resetPassword("userEmail", "newPassword");

        assertTrue(userDAO.userVerify("userEmail", "newPassword"));

    }

    @Test
    public void test9() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));

        userDAO.promote(1L, User.STATUS.Manager.name());

        assertEquals(User.STATUS.Manager.name(), userDAO.get("userEmail").orElseThrow(NoEntityException::new).getStatus());
    }

    @Test
    public void test10() throws DAOException, SQLException, NoEntityException {
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Manager.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Manager.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Manager.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Manager.name()));
        userDAO.save(new User(null, "userName", "userPassword", "userEmail", "userPhoneNumber", User.STATUS.Client.name()));

        assertEquals(3, userDAO.getPaginationByStatus(0, 3, User.STATUS.Admin.name()).size());
        assertEquals(2, userDAO.getPaginationByStatus(3, 3, User.STATUS.Admin.name()).size());

        assertEquals(1, userDAO.getPaginationByStatus(0, 3, User.STATUS.Manager.name()).size());
    }
}
