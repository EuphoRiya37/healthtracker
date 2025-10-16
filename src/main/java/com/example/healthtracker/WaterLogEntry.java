package com.example.healthtracker;

import java.time.LocalDate;

public class WaterLogEntry {

    private String username;
    private int glasses;
    private LocalDate date;

    public WaterLogEntry(String username, int glasses, LocalDate date) {
        this.username = username;
        this.glasses = glasses;
        this.date = date;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGlasses() {
        return glasses;
    }

    public void setGlasses(int glasses) {
        this.glasses = glasses;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}