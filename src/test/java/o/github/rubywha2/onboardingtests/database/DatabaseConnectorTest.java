package o.github.rubywha2.onboardingtests.database;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseConnectorTest {

    @Test
    void testGetConnection() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection()) {
            assertTrue(conn.isValid(2), "Connection should be valid");
        }
    }
}
