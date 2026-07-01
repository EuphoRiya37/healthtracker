# Contributing to Health Tracker

Thank you for your interest in contributing! Here's how you can help improve this project.

## Setup for Development

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- Git

### 1. Clone and Setup

```bash
git clone https://github.com/EuphoRiya37/healthtracker.git
cd healthtracker
mvn clean install
```

### 2. Run Locally

```bash
mvn spring-boot:run
```

Application runs at `http://localhost:8080`

### 3. Make Changes

- Edit Java files in `src/main/java/`
- Modify UI components in Vaadin views
- Update tests in `src/test/`

### 4. Test Your Changes

```bash
mvn test
```

## What to Work On

- Bug fixes (check existing issues)
- New health metrics to track
- UI/UX improvements
- Database optimization
- Documentation improvements
- Unit test coverage

## Before Submitting

- Ensure your changes compile: `mvn clean install`
- Run tests: `mvn test`
- Test the app locally: `mvn spring-boot:run`
- Check for console errors
- Follow Java naming conventions

## Reporting Issues

When reporting bugs:
- Describe the problem clearly
- Steps to reproduce
- Expected vs actual behavior
- Your Java/Maven version
- Any error messages from console

## Questions?

Open an issue or discussion in the repository!

---

**License:** MIT + Commons Clause (see LICENSE file)
