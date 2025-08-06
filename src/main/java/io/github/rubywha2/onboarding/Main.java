package io.github.rubywha2.onboarding;
import io.github.rubywha2.onboarding.gui.LoginGUI;
import io.github.rubywha2.onboarding.service.LoginService;
import io.github.rubywha2.onboarding.util.CSVLogger;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.println("Secure Staff Onboarding Program started.");
        CSVLogger logger = new CSVLogger();
        LoginService loginService = new LoginService();
        new LoginGUI(loginService);

        System.out.println("Please enter Y to view user log in records...");
        Scanner scanner = new Scanner(System.in);
        String viewLogs = scanner.nextLine();
        scanner.close();
        if (viewLogs.equals("Y")) {
            logger.printLogs();
        }

    }
}