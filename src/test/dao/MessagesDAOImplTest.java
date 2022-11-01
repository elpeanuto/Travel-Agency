package dao;

import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.servies.dao.MessagesDAO;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.dao.daoImpl.MessagesDAOImpl;
import edu.elpeanuto.tms.servies.dao.daoImpl.ProductDAOImpl;
import edu.elpeanuto.tms.servies.dao.daoImpl.UserDAOImpl;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.TestConnection;
import edu.elpeanuto.tms.servies.dto.MessageDTO;
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

public class MessagesDAOImplTest {
    private static MessagesDAO messagesDAO;
    private static DBConnection dbConnection;

    @BeforeAll
    public static void startUp(){
        dbConnection = new TestConnection();
        messagesDAO = new MessagesDAOImpl(dbConnection);
    }
    @BeforeEach
    public void setUp() throws DAOException {
        try (Connection con = dbConnection.getConnection();
             Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate("DROP TABLE IF EXISTS message");
            stmt.executeUpdate("DROP TABLE IF EXISTS messageanswer");
            stmt.executeUpdate("CREATE TABLE message" +
                    "(" +
                    "    id              INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "    messageAnswerId INT REFERENCES messageAnswer (id)," +
                    "    userId          INT                            NOT NULL," +
                    "    userEmail       VARCHAR(50)                    NOT NULL," +
                    "    userName        VARCHAR(50)                    NOT NULL," +
                    "    category        VARCHAR(50)                    NOT NULL," +
                    "    message         VARCHAR(500)                   NOT NULL," +
                    "    receivedDate    VARCHAR(50)                    NOT NULL" +
                    ");");
            stmt.executeUpdate("CREATE TABLE messageAnswer" +
                    "(" +
                    "    id             INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "    adminId        INT," +
                    "    answer         VARCHAR(500)," +
                    "    processingDate VARCHAR(500)" +
                    ");");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Test
    public void test1() throws DAOException, SQLException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));


        assertEquals(2, messagesDAO.getNumberOfNotesByUserId(1L).orElseThrow(NoEntityException::new));
    }

    @Test
    public void test2() throws DAOException, SQLException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));


        assertEquals(2, messagesDAO.getNumberOfNotesNotAnswered().orElseThrow(NoEntityException::new));
    }

    @Test
    public void test3() throws DAOException, SQLException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));


        assertEquals(2, messagesDAO.getAll().size());
    }
    @Test
    public void test4() throws DAOException, SQLException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.update(new Message(1L, null, 1L, "email", "name", "category", "message2", (new Date()).toString()));


        assertEquals("message2", messagesDAO.get(1L).orElseThrow(NoEntityException::new).getMessage());
    }

    @Test
    public void test5() throws DAOException, SQLException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.adminUpdate(new MessageDTO(1L, 1L, "response", "processingDate"));


        assertEquals("processingDate", messagesDAO.get(1L).orElseThrow(NoEntityException::new).getProcessingDate());
    }

    @Test
    public void test6() throws DAOException, SQLException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));


        assertEquals(3, messagesDAO.getPaginationByUserId(0, 3, 1L).size());
        assertEquals(2, messagesDAO.getPaginationByUserId(3, 3, 1L).size());
    }

    @Test
    public void test7() throws DAOException, SQLException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", (new Date()).toString()));


        assertEquals(3, messagesDAO.getPaginationNotAnswered(0, 3).size());
        assertEquals(2, messagesDAO.getPaginationNotAnswered(3, 3).size());
    }
}
