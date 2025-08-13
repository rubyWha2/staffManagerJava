package o.github.rubywha2.onboardingtests;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

import io.github.rubywha2.onboarding.Main;

class MainTest {

    @Test
    void testMain_WithYInput() {
        // Simulate user input "Y" for viewing logs
        String simulatedInput = "Y\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Run main method
        Main.main(new String[]{});

        // Restore original System.in
        System.setIn(originalIn);

        // There is no return value from main(), but this ensures it runs without exceptions
        assertTrue(true);
    }

    @Test
    void testMain_WithOtherInput() {
        // Simulate user input other than "Y" (e.g., "N")
        String simulatedInput = "N\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Run main method
        Main.main(new String[]{});

        // Restore original System.in
        System.setIn(originalIn);

        // Main runs successfully without throwing exceptions
        assertTrue(true);
    }
}
