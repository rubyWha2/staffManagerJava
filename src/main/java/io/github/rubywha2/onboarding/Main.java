package io.github.rubywha2.onboarding;
import io.github.rubywha2.onboarding.gui.LoginGUI;
import io.github.rubywha2.onboarding.service.LoginService;


public class Main {
    public static void main(String[] args) {
        System.out.println("Secure Staff Onboarding Program started.");

        LoginService loginService = new LoginService();
        new LoginGUI(loginService);
    }
}