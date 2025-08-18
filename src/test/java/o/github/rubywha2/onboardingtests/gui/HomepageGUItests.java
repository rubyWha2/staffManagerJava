package o.github.rubywha2.onboardingtests.gui;

import org.assertj.swing.edt.GuiActionRunner;
import io.github.rubywha2.onboarding.gui.HomepageGUI;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class HomepageGUITest {
    private FrameFixture window;

    @BeforeEach
    void setUp() {
        // Create HomepageGUI in the Swing EDT (thread-safe way)
        HomepageGUI homepage = GuiActionRunner.execute(HomepageGUI::new);

        // Find the JFrame created by HomepageGUI
        JFrame frame = GuiActionRunner.execute(() -> {
            for (Frame f : JFrame.getFrames()) {
                if (f.isVisible() && f.getTitle().equals("Homepage")) {
                    return (JFrame) f;
                }
            }
            return null;
        });

        assertNotNull(frame, "Homepage frame should not be null");
        window = new FrameFixture(frame);
        window.show(); // show the window for testing
    }

    @AfterEach
    void tearDown() {
        window.cleanUp(); // closes the GUI after test
    }

    @Test
    void testHomepageHasWelcomeLabel() {
        window.label().requireText("Welcome to Staff Manager Homepage");
    }

    @Test
    void testButtonsExist() {
        window.button("Staff").requireVisible();
        window.button("Training").requireVisible();
        window.button("Job Fit").requireVisible();
        window.button("Job Roles").requireVisible();
        window.button("Log Out").requireVisible();
    }

    @Test
    void testLogoutButtonClosesFrame() {
        window.button("Log Out").click();
        assertFalse(window.target().isVisible(), "Frame should close after logout");
    }
}

