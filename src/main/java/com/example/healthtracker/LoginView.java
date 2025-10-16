package com.example.healthtracker;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm loginForm = new LoginForm();
    private final AuthService authService;

    // AuthService will be automatically provided by Spring
    public LoginView(AuthService authService) {
        this.authService = authService;

        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // This is where the login form will send the credentials
        loginForm.setAction("login");

        // Listen for login events
        loginForm.addLoginListener(e -> authenticateUser(e.getUsername(), e.getPassword()));

        add(new H1("Vitality Login"), loginForm);
    }

    private void authenticateUser(String username, String password) {
        if (authService.authenticate(username, password)) {
            // If login is successful, redirect to the main water log page
            UI.getCurrent().navigate("water");
        } else {
            // If login fails, show an error message
            loginForm.setError(true);
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // If the user is already logged in, redirect them to the main page
        if (authService.isUserLoggedIn()) {
            event.forwardTo("water");
        }
    }
}