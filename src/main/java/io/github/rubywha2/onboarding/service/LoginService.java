package io.github.rubywha2.onboarding.service;

import io.github.rubywha2.onboarding.dao.UserDAO;
import io.github.rubywha2.onboarding.model.Users;
import org.mindrot.jbcrypt.BCrypt;

public class LoginService {
    private final UserDAO userDAO = new UserDAO();

    public boolean validateLogin(String username, String password) {
        Users user = userDAO.getUserByUsername(username);
        if (user == null) {
            System.out.println("No user found for username: " + username);
            return false;
        }
        String storedHash = user.getPasswordHash();

        String hashedPassword = BCrypt.hashpw("test", BCrypt.gensalt());
        System.out.println(hashedPassword);

        System.out.println("✅ User found: " + username);
        System.out.println("🔐 Input password: " + password);
        System.out.println("🧾 Stored hash: " + storedHash);
        System.out.println("🧮 Hash length: " + storedHash.length());

        boolean result = BCrypt.checkpw(password, storedHash);
        System.out.println("✅ Password match result: " + result);

        return result;
    }

}
