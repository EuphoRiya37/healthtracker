package com.example.healthtracker;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineReminderService {

    private final List<MedicineReminder> reminders = new ArrayList<>();

    public void addReminder(String medicineName, String dosage, LocalDateTime reminderTime) {
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        if (username != null) {
            reminders.add(new MedicineReminder(username, medicineName, dosage, reminderTime));
        }
    }

    public List<MedicineReminder> getRemindersForCurrentUser() {
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        if (username == null) {
            return new ArrayList<>();
        }
        return reminders.stream()
                .filter(reminder -> username.equals(reminder.getUsername()))
                .collect(Collectors.toList());
    }
}