package com.example.healthtracker;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant; // Correct import
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

@CssImport("./styles.css")
public class MainLayout extends AppLayout {

    private final AuthService authService;

    public MainLayout(@Autowired AuthService authService) {
        this.authService = authService;
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Vitality"); // Updated logo text
        logo.addClassNames(
                "logo-text", // Custom class for styling
                LumoUtility.Margin.SMALL
        );

        RouterLink waterLogLink = new RouterLink("Water Log", WaterLogView.class);
        RouterLink tdeeCalculatorLink = new RouterLink("TDEE Calculator", TdeeCalculatorView.class);
        RouterLink medicineReminderLink = new RouterLink("Medicine Reminder", MedicineReminderView.class);

        Button logoutButton = new Button("Logout", e -> authService.logout());
        logoutButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY); // Corrected this line

        HorizontalLayout header = new HorizontalLayout(logo, waterLogLink, tdeeCalculatorLink, medicineReminderLink, logoutButton);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
                "main-layout-header", // Custom class for styling
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }
}