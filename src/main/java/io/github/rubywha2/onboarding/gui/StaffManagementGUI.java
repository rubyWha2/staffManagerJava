package io.github.rubywha2.onboarding.gui;

import javax.swing.*;
import java.awt.*;

public class StaffManagementGUI {
    public StaffManagementGUI() {
        createWindow(); // call method to build GUI
    }

    private void createWindow() {
        JFrame frame = new JFrame("Staff Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Manual layout for full control
        frame.setVisible(true);

        // Sidebar panel on the left
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(200, 200, 200));
        sidebar.setBounds(0, 0, 200, 800); // width: 200px
        sidebar.setLayout(null);
        frame.add(sidebar);

        // Main panel (for content, buttons etc)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(200, 0, 900, 800); // right of sidebar
        frame.add(contentPanel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 120, 40);
        sidebar.add(backButton);

        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame
        });
    }
}

