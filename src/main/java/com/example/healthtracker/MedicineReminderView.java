package com.example.healthtracker;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

import java.time.LocalDateTime;

@PageTitle("Medicine Reminder")
@Route(value = "medicine", layout = MainLayout.class)
public class MedicineReminderView extends VerticalLayout implements BeforeEnterObserver {

    private final MedicineReminderService reminderService;
    private final AuthService authService;

    private final TextField medicineName = new TextField("Medicine Name");
    private final TextField dosage = new TextField("Dosage (e.g., 500mg)");
    private final DateTimePicker reminderTime = new DateTimePicker("Reminder Time");
    private final Button setReminderButton = new Button("Set Reminder");
    private final Grid<MedicineReminder> grid = new Grid<>(MedicineReminder.class);

    public MedicineReminderView(MedicineReminderService reminderService, AuthService authService) {
        this.reminderService = reminderService;
        this.authService = authService;

        setAlignItems(Alignment.CENTER);
        addClassName("content-panel");

        H2 title = new H2("💊 Medicine Reminder");

        configureGrid();

        setReminderButton.addClickListener(e -> setReminder());

        add(title, medicineName, dosage, reminderTime, setReminderButton, grid);
        updateGrid();
    }

    private void configureGrid() {
        grid.setColumns("medicineName", "dosage", "reminderTime");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setWidth("100%");
    }

    private void setReminder() {
        String name = medicineName.getValue();
        String dose = dosage.getValue();
        LocalDateTime time = reminderTime.getValue();

        if (name.isEmpty() || dose.isEmpty() || time == null) {
            Notification.show("Please fill all fields.", 3000, Notification.Position.MIDDLE);
            return;
        }

        reminderService.addReminder(name, dose, time);
        Notification.show("Reminder set!");

        medicineName.clear();
        dosage.clear();
        reminderTime.clear();

        updateGrid();
    }

    // FIX: Re-added the missing updateGrid method
    private void updateGrid() {
        grid.setItems(reminderService.getRemindersForCurrentUser());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!authService.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }
}