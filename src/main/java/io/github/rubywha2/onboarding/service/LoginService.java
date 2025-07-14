package io.github.rubywha2.onboarding.service;

import io.github.rubywha2.onboarding.dao.UserDAO;
import io.github.rubywha2.onboarding.model.Users;
import org.mindrot.jbcrypt.BCrypt;

public class LoginService {
    private final UserDAO userDAO = new UserDAO();

    public boolean authenticate(String username, String password) {
        Users user = userDAO.getUserByUsername(username);
        if (user == null) return false;

        return BCrypt.checkpw(password, user.getPasswordHash());
    }


}
