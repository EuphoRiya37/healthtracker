# Health Tracker

A simple web application for tracking personal health data including weight, BMI, medicines, and other health metrics. Built with Java and Vaadin.

---

## What It Does

Health Tracker lets you:
- 📊 **Log health metrics** — weight, BMI, medicine intake
- 📈 **Track progress over time** — visualize trends
- 💾 **Store data** — persistent storage of all entries
- 📋 **Manage records** — view, edit, delete health data

Perfect for monitoring your health journey or managing chronic conditions with medicine tracking.

---

## Tech Stack

- **Language:** Java
- **Framework:** Vaadin (enterprise Java web UI framework)
- **Build:** Maven
- **Frontend:** Vaadin Flow with compiled web components

---

## Setup & Installation

### Prerequisites

- **Java 11** or higher
- **Maven 3.6** or higher
- **Git**

### 1. Clone the Repository

```bash
git clone https://github.com/EuphoRiya37/healthtracker.git
cd healthtracker
```

### 2. Build with Maven

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or if it's a Jetty/Tomcat app:

```bash
mvn jetty:run
```

The app should open at **http://localhost:8080**

---

## Usage

1. **Open the app** in your browser
2. **Enter your health data:**
   - Current weight
   - Height (for BMI calculation)
   - Medicine names and dosage
   - Other relevant health metrics
3. **Submit** — data is saved to the database
4. **View history** — see all your logged entries
5. **Track progress** — monitor trends over time

---

## Project Structure

```
src/
  main/
    java/          - Java backend code
    resources/     - Configuration files (application.properties, etc.)
    webapp/        - Web resources
  test/            - Unit tests
pom.xml            - Maven configuration and dependencies
```

---

## Database

Health Tracker uses a relational database to store:
- User health records
- Metrics history
- Medicine tracking data

Configure database connection in `application.properties` or environment variables.

---

## Features

- ✅ Simple, intuitive UI
- ✅ Input form for health data
- ✅ Persistent storage
- ✅ Track multiple metrics
- ✅ View historical data
- ✅ Medicine dosage tracking

---

## Development

To set up for local development:

```bash
# Build the project
mvn clean install

# Run with hot reload
mvn spring-boot:run
```

Changes to Java files will be recompiled on server restart.

---

## Contributing

Found a bug or have an idea? See [CONTRIBUTING.md](CONTRIBUTING.md)

---

## License

This project is licensed under MIT + Commons Clause — see [LICENSE](LICENSE) for details.

Free for personal and educational use. Contact for commercial licensing.

---

## Notes

This is a learning project demonstrating basic CRUD operations and Java web development with Vaadin. It's a great starting point for understanding how to build web applications in Java.
