package com.example.healthtracker;

import org.springframework.stereotype.Service;

@Service
public class TdeeService {

    // Calculates TDEE using the Mifflin-St Jeor equation
    public double calculateTdee(double weightKg, double heightCm, int age, String activityLevel, String gender) {
        // Step 1: Calculate BMR (Basal Metabolic Rate)
        double bmr;
        if ("male".equalsIgnoreCase(gender)) {
            bmr = (10 * weightKg) + (6.25 * heightCm) - (5 * age) + 5;
        } else { // female
            bmr = (10 * weightKg) + (6.25 * heightCm) - (5 * age) - 161;
        }

        // Step 2: Apply activity multiplier
        double activityMultiplier;
        switch (activityLevel) {
            case "Sedentary":
                activityMultiplier = 1.2;
                break;
            case "Lightly Active":
                activityMultiplier = 1.375;
                break;
            case "Moderately Active":
                activityMultiplier = 1.55;
                break;
            case "Very Active":
                activityMultiplier = 1.725;
                break;
            default:
                activityMultiplier = 1.2;
                break;
        }
        return bmr * activityMultiplier;
    }
}