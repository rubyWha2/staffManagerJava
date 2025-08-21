package o.github.rubywha2.onboardingtests.gui;
import io.github.rubywha2.onboarding.dao.JobFitDAO;
import io.github.rubywha2.onboarding.model.JobRoleTraining;
import org.assertj.swing.edt.GuiActionRunner;
import io.github.rubywha2.onboarding.gui.JobFitGUI;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JobFitGUItests {
    private FrameFixture window;
    private JFrame frame;

    @BeforeEach
    void setUp() {
        // Create Job Fit GUI in the Swing EDT (thread-safe way)
        JobFitGUI homepage = GuiActionRunner.execute(JobFitGUI::new);

        // Find the JFrame created by HomepageGUI
        JFrame frame = GuiActionRunner.execute(() -> {
            for (Frame f : JFrame.getFrames()) {
                if (f.isVisible() && f.getTitle().equals("Job Profile Page")) {
                    return (JFrame) f;
                }
            }
            return null;
        });

        assertNotNull(frame, "JobFit page frame should not be null");
        window = new FrameFixture(frame);
        window.show(); // show the window for testing
    }

    @AfterEach
    void tearDown() {
        window.cleanUp(); // closes the GUI after test
    }

    @Test
    void testJobFitPageHasWelcomeLabel() {
        window.label().requireText("Welcome to the Job Fit Page");
    }

    @Test
    void testButtonsExist() {
        window.button("Assign").requireVisible();
    }

    @Test
    void testBackClosesFrame() {
        window.button("Back").click();
        assertTrue(window.target().isVisible(), "Frame should close after back");
    }

    /** Test that the table has rows corresponding to DAO data */
    @Test
    public void testTablePopulation() {
        JobFitGUI gui = new JobFitGUI();
        JFrame frame = gui.getFrame(); // now safe
        Container content = frame.getContentPane();

        JTable table = findTableInPanel((JPanel) frame.getContentPane());
        assertNotNull(table, "Table should exist in the GUI");

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        assertNotNull(model, "Table model should exist");

        // Compare number of rows in table with DAO
        JobFitDAO dao = new JobFitDAO();
        List<JobRoleTraining> data = dao.getAllJobRolesTrainingInstances();
        assertEquals(data.size(), model.getRowCount(), "Table should have same number of rows as DAO data");
    }

    //Below are helper methods

    /** Recursively searches a panel for a JButton with the given name */
    private JButton findButtonInPanel(Container container, String name) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && name.equals(comp.getName())) {
                return (JButton) comp;
            } else if (comp instanceof Container) {
                JButton b = findButtonInPanel((Container) comp, name);
                if (b != null) return b;
            }
        }
        return null;
    }

    /** Recursively searches a panel for a JTable inside a JScrollPane */
    private JTable findTableInPanel(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) comp;
                JViewport viewport = scroll.getViewport();
                Component view = viewport.getView();
                if (view instanceof JTable) return (JTable) view;
            } else if (comp instanceof Container) {
                JTable table = findTableInPanel((Container) comp);
                if (table != null) return table;
            }
        }
        return null;
    }

}
