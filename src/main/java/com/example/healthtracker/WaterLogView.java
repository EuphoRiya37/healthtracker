package com.example.healthtracker;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

@PageTitle("Daily Water Log")
@Route(value = "water", layout = MainLayout.class)
public class WaterLogView extends VerticalLayout implements BeforeEnterObserver {

    private final WaterLogService waterLogService;
    private final AuthService authService;

    private final Grid<WaterLogEntry> grid = new Grid<>(WaterLogEntry.class);
    private final IntegerField glassesInput = new IntegerField("Number of glasses");

    public WaterLogView(WaterLogService waterLogService, AuthService authService) {
        this.waterLogService = waterLogService;
        this.authService = authService;

        setAlignItems(Alignment.CENTER);
        addClassName("content-panel"); // <--- ADDED CLASS

        H2 title = new H2("💧 Daily Water Log");
        glassesInput.setPlaceholder("e.g., 8");
        Button addButton = new Button("Add to Log");

        configureGrid();

        addButton.addClickListener(e -> addWaterLogEntry());

        add(title, glassesInput, addButton, grid);
        updateGrid();
    }

    private void configureGrid() {
        grid.setColumns("glasses", "date");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setWidth("100%");
    }

    private void addWaterLogEntry() {
        if (glassesInput.getValue() != null && glassesInput.getValue() > 0) {
            waterLogService.addEntry(glassesInput.getValue());
            Notification.show("Entry saved!");
            glassesInput.clear();
            updateGrid();
        } else {
            Notification.show("Please enter a valid number.", 3000, Notification.Position.MIDDLE);
        }
    }

    private void updateGrid() {
        grid.setItems(waterLogService.getEntriesForCurrentUser());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!authService.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }
}