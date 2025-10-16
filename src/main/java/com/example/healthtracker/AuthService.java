package com.example.healthtracker;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service // Tells Spring to manage this class
public class AuthService {

    // Our simple "user database"
    private final Map<String, String> users = new HashMap<>();

    public AuthService() {
        // Pre-saved data as you requested
        users.put("riya123", "admin");
        users.put("girish123", "admin");
        users.put("lakshita123", "admin");
    }

    public boolean authenticate(String username, String password) {
        // Check if the username exists and if the password matches
        if (users.containsKey(username) && users.get(username).equals(password)) {
            // If successful, store the username in the current session
            VaadinSession.getCurrent().setAttribute("username", username);
            return true;
        }
        return false;
    }

    // This is the missing method
    public boolean isUserLoggedIn() {
        // Check if the "username" attribute exists in the session
        return VaadinSession.getCurrent().getAttribute("username") != null;
    }

    public void logout() {
        // Invalidate the session to log the user out
        VaadinSession.getCurrent().getSession().invalidate();

        // Redirect the user's browser to the root (landing page)
        UI.getCurrent().getPage().setLocation("/");
    }
}