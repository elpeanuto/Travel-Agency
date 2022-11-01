package other;

import edu.elpeanuto.tms.servies.PasswordHashing;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordHashingTest {
    @Test
    public void hashTest() {
        String password = "admin";

        String hashedPassword = PasswordHashing.hashPassword(password);
        String hashedPassword2 = PasswordHashing.hashPassword(password);

        assertEquals(hashedPassword, hashedPassword2);
    }
}
