package io.github.rubywha2.onboarding.gui;

import javax.swing.*;

public class JobFitGUI {
    public JobFitGUI() {
        createWindow(); // call method to build GUI
    }

    private void createWindow() {
        JFrame frame = new JFrame("Job Profile Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Manual layout for full control
        frame.setVisible(true);

        JButton backButton = new JButton("Back");
        backButton.setBounds(1100, 10, 120, 40);
        frame.add(backButton);

        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame
        });

    }
}