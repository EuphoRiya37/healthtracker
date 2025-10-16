package com.example.healthtracker;

import java.time.LocalDateTime;

public class MedicineReminder {

    private String username;
    private String medicineName;
    private String dosage;
    private LocalDateTime reminderTime;

    public MedicineReminder(String username, String medicineName, String dosage, LocalDateTime reminderTime) {
        this.username = username;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.reminderTime = reminderTime;
    }

    // --- Getters and Setters ---
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public LocalDateTime getReminderTime() { return reminderTime; }
    public void setReminderTime(LocalDateTime reminderTime) { this.reminderTime = reminderTime; }
}