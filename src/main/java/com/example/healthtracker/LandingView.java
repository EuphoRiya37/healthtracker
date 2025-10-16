package com.example.healthtracker;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout; // ADDED for title spacing
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
@AnonymousAllowed
public class LandingView extends VerticalLayout {

    public LandingView() {
        // --- Page Setup ---
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        // FIX: Changed FLEX_START to START
        setJustifyContentMode(JustifyContentMode.START);
        addClassName("landing-view");

        // --- Header with Login/Sign Up ---
        HorizontalLayout topHeader = new HorizontalLayout();
        topHeader.addClassName("landing-header");
        topHeader.setWidthFull();
        topHeader.setAlignItems(Alignment.CENTER);

        H1 appLogo = new H1("Vitality");
        appLogo.addClassName("app-logo");
        topHeader.add(appLogo);

        Button loginButton = new Button("Login", e -> UI.getCurrent().navigate("login"));
        loginButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Button signUpButton = new Button("Sign Up", e -> UI.getCurrent().navigate("signup"));
        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SMALL);
        topHeader.add(loginButton, signUpButton);

        add(topHeader);

        // --- Main Content Area ---
        VerticalLayout mainContent = new VerticalLayout();
        mainContent.addClassName("main-content"); // Class for scrolling/offset fix
        mainContent.setAlignItems(Alignment.CENTER);
        mainContent.setJustifyContentMode(JustifyContentMode.CENTER);
        mainContent.setWidthFull();

        H1 title = new H1("Track Your Health. Master Your Life.");
        Paragraph subtitle = new Paragraph("Your all-in-one companion for managing water intake, calories, medication, and more.");
        Button exploreButton = new Button("Explore Features", e -> UI.getCurrent().navigate("login"));
        exploreButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        exploreButton.addClassName("explore-button");

        mainContent.add(title, subtitle, exploreButton);

        // --- Feature Section ---
        VerticalLayout hydrationCard = createFeatureCard(
                "Never Forget to Hydrate",
                "Log your daily water intake with a single click. Our tracker helps you stay refreshed and meet your hydration goals.",
                "/images/water.jpg",
                VaadinIcon.DROP
        );

        VerticalLayout calorieCard = createFeatureCard(
                "Understand Your Calorie Needs",
                "Our TDEE calculator gives you a scientific estimate of your daily calorie needs for smarter dietary choices.",
                "/images/tdee.jpg",
                VaadinIcon.FIRE // FIX: Changed FLAME to FIRE
        );

        VerticalLayout medicineCard = createFeatureCard(
                "Medication Reminders",
                "Set personalized reminders for your medications. Never miss a dose and stay on top of your health regimen.",
                "/images/medicine.jpg",
                VaadinIcon.PILL
        );

        HorizontalLayout featureRow1 = new HorizontalLayout(hydrationCard, calorieCard, medicineCard);
        featureRow1.setWidth("80%");
        featureRow1.setJustifyContentMode(JustifyContentMode.CENTER);
        featureRow1.addClassNames(
                LumoUtility.Gap.XLARGE,
                LumoUtility.FlexWrap.WRAP
        );

        mainContent.add(featureRow1);

        add(mainContent);
    }

    // FIX: Updated to use HorizontalLayout for the title container (fixes setSpacing error)
    private VerticalLayout createFeatureCard(String title, String description, String imagePath, VaadinIcon iconType) {
        VerticalLayout card = new VerticalLayout();
        card.addClassName("feature-card");
        card.setAlignItems(Alignment.CENTER);
        card.setSpacing(false);
        card.setWidth("350px");

        Image featureImage = new Image(imagePath, title);
        featureImage.addClassName("feature-image");
        card.add(featureImage);

        // Title setup: Use HorizontalLayout to correctly manage spacing between icon and text
        Icon cardIcon = new Icon(iconType);
        cardIcon.addClassName("feature-icon");

        H3 featureTitleText = new H3(title);
        featureTitleText.addClassNames(LumoUtility.Margin.Vertical.NONE, LumoUtility.Margin.Horizontal.NONE);

        HorizontalLayout featureTitleContainer = new HorizontalLayout(cardIcon, featureTitleText);
        featureTitleContainer.setSpacing(true);
        featureTitleContainer.setAlignItems(Alignment.CENTER);
        featureTitleContainer.addClassNames(LumoUtility.Padding.Vertical.XSMALL);

        card.add(featureTitleContainer);

        Paragraph featureDescription = new Paragraph(description);
        card.add(featureDescription);

        return card;
    }
}