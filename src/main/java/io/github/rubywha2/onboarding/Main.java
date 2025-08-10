package io.github.rubywha2.onboarding;

import io.github.rubywha2.onboarding.gui.LoginGUI;
import io.github.rubywha2.onboarding.service.LoginService;
import io.github.rubywha2.onboarding.util.CSVLogger;
import java.util.Scanner;

public class Main {

    /**
     * The main entry point for the Secure Staff Onboarding Program.
     * Initializes logging and login service, starts the login GUI,
     * and optionally displays login records on user input.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Inform user the program has started
        System.out.println("Secure Staff Onboarding Program started.");

        // Initialize CSV logger to handle login records
        CSVLogger logger = new CSVLogger();

        // Initialize the login service responsible for authentication logic
        LoginService loginService = new LoginService();

        // Launch the login GUI and pass the login service to it
        new LoginGUI(loginService);

        // Prompt user to see if they want to view login records
        System.out.println("Please enter Y to view user log in records...");

        // Read user input from the console
        Scanner scanner = new Scanner(System.in);
        String viewLogs = scanner.nextLine();

        // Close scanner resource
        scanner.close();

        // If user entered 'Y', display the login records from CSV logger
        if (viewLogs.equals("Y")) {
            logger.printLogs();
        }
    }
}
