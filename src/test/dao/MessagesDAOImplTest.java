package dao;

import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.servies.dao.MessagesDAO;
import edu.elpeanuto.tms.servies.dao.daoImpl.MessagesDAOImpl;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            stmt.executeUpdate("DROP TABLE IF EXISTS messages");
            stmt.executeUpdate("DROP TABLE IF EXISTS message_answer");
            stmt.executeUpdate("CREATE TABLE messages" +
                    "(" +
                    "    id              INT PRIMARY KEY AUTO_INCREMENT," +
                    "    message_answer_id INT REFERENCES message_answer (id)," +
                    "    user_id          INT                        NOT NULL," +
                    "    user_email       VARCHAR(50)                NOT NULL," +
                    "    user_name        VARCHAR(20)                NOT NULL," +
                    "    category         VARCHAR(20)                NOT NULL," +
                    "    message          VARCHAR(500)               NOT NULL," +
                    "    received_date    DATETIME                   NOT NULL" +
                    ");");
            stmt.executeUpdate("CREATE TABLE message_answer" +
                    "(" +
                    "    id             INT PRIMARY KEY AUTO_INCREMENT," +
                    "    admin_id        INT," +
                    "    answer         VARCHAR(500)," +
                    "    processing_date DATETIME" +
                    ");");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Test
    public void test1() throws DAOException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        assertEquals(2, messagesDAO.getNumberOfNotesByUserId(1L).orElseThrow(NoEntityException::new));
    }

    @Test
    public void test2() throws DAOException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        assertEquals(2, messagesDAO.getNumberOfNotesNotAnswered().orElseThrow(NoEntityException::new));
    }

    @Test
    public void test3() throws DAOException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        assertEquals(2, messagesDAO.getAll().size());
    }
    @Test
    public void test4() throws DAOException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.update(new Message(1L, null, 1L, "email", "name", "category", "message2", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        assertEquals("message2", messagesDAO.get(1L).orElseThrow(NoEntityException::new).getMessage());
    }

    @Test
    public void test5() throws DAOException, NoEntityException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.adminUpdate(new MessageDTO(1L, 1L, "response", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        assertEquals(1L, messagesDAO.get(1L).orElseThrow(NoEntityException::new).getAdminId());
    }

    @Test
    public void test6() throws DAOException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        assertEquals(3, messagesDAO.getPaginationByUserId(0, 3, 1L).size());
        assertEquals(2, messagesDAO.getPaginationByUserId(3, 3, 1L).size());
    }

    @Test
    public void test7() throws DAOException {
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        messagesDAO.save(new Message(null, null, 1L, "email", "name", "category", "message", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        assertEquals(3, messagesDAO.getPaginationNotAnswered(0, 3).size());
        assertEquals(2, messagesDAO.getPaginationNotAnswered(3, 3).size());
    }
}
