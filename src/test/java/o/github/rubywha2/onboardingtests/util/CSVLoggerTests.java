package o.github.rubywha2.onboardingtests.util;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import io.github.rubywha2.onboarding.util.CSVLogger;
import io.github.rubywha2.onboarding.model.Log;

import static org.junit.jupiter.api.Assertions.*;

class CSVLoggerTest {

    private static final String TEST_FILE_PATH = "logs/login_logs.csv";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() throws IOException {
        // Redirect System.out and System.err to capture print output
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));

        // Ensure logs directory exists
        Files.createDirectories(Paths.get("logs"));

        // Create an empty test CSV file
        Files.write(Paths.get(TEST_FILE_PATH), "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the test CSV file
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));

        // Restore original System.out and System.err
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testCsvExists() {
        // CSV file exists
        assertTrue(CSVLogger.csvExists());

        // Delete file to test non-existence
        try { Files.delete(Paths.get(TEST_FILE_PATH)); } catch (IOException ignored) {}
        assertFalse(CSVLogger.csvExists());
    }

    @Test
    void testAppendAndPrintLogs() throws IOException {
        // Create a Log object
        Log log = new Log("testuser", "2025-08-13 12:00", 1);

        // Append log with status "SuccessfulLogin"
        CSVLogger.append(log, "SuccessfulLogin");

        // Read file and verify contents
        String content = new String(Files.readAllBytes(Paths.get(TEST_FILE_PATH)));
        assertTrue(content.contains("testuser"));
        assertTrue(content.contains("SuccessfulLogin"));

        // Test printLogs output
        CSVLogger.printLogs();
        String output = outContent.toString();
        assertTrue(output.contains("testuser"));
        assertTrue(output.contains("SuccessfulLogin"));
    }

    @Test
    void testAppendWithoutCsv() throws IOException {
        // Delete CSV to simulate missing file
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));

        Log log = new Log("user2", "2025-08-13 12:01", 2);
        CSVLogger.append(log, "FailedLogin");

        // Should print error about missing file
        String output = outContent.toString();
        assertTrue(output.contains("CSV file does not exist"));
    }

    @Test
    void testPrintLogsWithoutCsv() throws IOException {
        // Delete CSV to simulate missing file
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));

        CSVLogger.printLogs();

        // Should print error about missing file
        String output = outContent.toString();
        assertTrue(output.contains("CSV file does not exist"));
    }
}
