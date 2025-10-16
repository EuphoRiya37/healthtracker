package com.example.healthtracker;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaterLogService {

    private final List<WaterLogEntry> waterLogEntries = new ArrayList<>();

    public void addEntry(int glasses) {
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        if (username != null) {
            waterLogEntries.add(new WaterLogEntry(username, glasses, LocalDate.now()));
        }
    }

    public List<WaterLogEntry> getEntriesForCurrentUser() {
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        if (username == null) {
            return new ArrayList<>();
        }
        return waterLogEntries.stream()
                .filter(entry -> username.equals(entry.getUsername()))
                .collect(Collectors.toList());
    }
}