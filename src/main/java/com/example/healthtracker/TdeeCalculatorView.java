package com.example.healthtracker;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

@PageTitle("TDEE Calculator")
@Route(value = "tdee", layout = MainLayout.class)
public class TdeeCalculatorView extends VerticalLayout implements BeforeEnterObserver {

    private final TdeeService tdeeService;
    private final AuthService authService;

    private final IntegerField ageField = new IntegerField("Age");
    private final NumberField weightField = new NumberField("Weight (kg)");
    private final NumberField heightField = new NumberField("Height (cm)");
    private final Select<String> genderSelect = new Select<>();
    private final Select<String> activityLevel = new Select<>();
    private final Button calculateButton = new Button("Calculate TDEE");

    public TdeeCalculatorView(TdeeService tdeeService, AuthService authService) {
        this.tdeeService = tdeeService;
        this.authService = authService;

        setAlignItems(Alignment.CENTER);
        addClassName("content-panel"); // <--- ADDED CLASS

        H2 title = new H2("⚖️ TDEE Calculator");

        configureFields();

        calculateButton.addClickListener(e -> calculateAndShowTdee());

        add(title, genderSelect, ageField, weightField, heightField, activityLevel, calculateButton);
    }

    private void configureFields() {
        genderSelect.setLabel("Gender");
        genderSelect.setItems("Male", "Female");

        activityLevel.setLabel("Activity Level");
        activityLevel.setItems("Sedentary", "Lightly Active", "Moderately Active", "Very Active");
    }

    private void calculateAndShowTdee() {
        try {
            double weight = weightField.getValue();
            double height = heightField.getValue();
            int age = ageField.getValue();
            String gender = genderSelect.getValue();
            String activity = activityLevel.getValue();

            if (gender == null || activity == null) {
                Notification.show("Please fill all fields.", 3000, Notification.Position.MIDDLE);
                return;
            }

            double result = tdeeService.calculateTdee(weight, height, age, activity, gender);
            String message = String.format("Your estimated daily calorie need is %.0f kcal.", result);
            Notification.show(message, 5000, Notification.Position.MIDDLE);

        } catch (NullPointerException e) {
            Notification.show("Please fill all fields with valid numbers.", 3000, Notification.Position.MIDDLE);
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!authService.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }
}