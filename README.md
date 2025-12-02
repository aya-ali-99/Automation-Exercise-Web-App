# 🚀 Automation Exercise Web App Test Automation

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.37.0-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-FF7F00?style=for-the-badge&logo=testng&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Rest Assured](https://img.shields.io/badge/Rest_Assured-5.5.6-007EC6?style=for-the-badge&logo=postman&logoColor=white)
![Allure](https://img.shields.io/badge/Allure-Report-FF7F00?style=for-the-badge&logo=qameta&logoColor=white)

## 📖 Project Description

This project delivers a robust and scalable **Test Automation Framework** for the [Automation Exercise](https://automationexercise.com/) website. Built with **Java** and **Selenium WebDriver**, it employs the **Page Object Model (POM)** design pattern to ensure code maintainability and reusability. The framework is designed to validate critical user journeys including User Registration, Login, Product Management, Cart Operations, and Checkout Workflows.

Detailed reporting is powered by **Allure**, providing rich, interactive visualizations of test execution results, including steps, logs, and screenshots.

---

## ✨ Key Features

- **Page Object Model (POM)**: Separation of page objects and test logic for better maintainability.
- **Data-Driven Testing**: Externalized test data using JSON files for flexible test scenarios.
- **Fluent Interface**: Method chaining in page objects for readable and expressive tests.
- **Robust Reporting**: Integrated **Allure Reports** for comprehensive test insights.
- **Cross-Browser Support**: Configurable driver management for running tests on different browsers.
- **CI/CD Ready**: Maven-based build system easy to integrate with Jenkins, GitHub Actions, etc.
- **Utility Libraries**: Custom utilities for File I/O, JSON parsing, and explicit waits.

---

## 🛠️ Technologies & Tools

| Category | Tool | Version |
|----------|------|---------|
| **Language** | Java | 21 |
| **Web Automation** | Selenium WebDriver | 4.37.0 |
| **Test Runner** | TestNG | 7.11.0 |
| **Build Tool** | Maven | 3.8+ |
| **Reporting** | Allure | 2.30.0 |
| **API Testing** | Rest Assured | 5.5.6 |
| **Logging** | Log4j2 | 2.25.2 |
| **Data Parsing** | JsonPath | 2.9.0 |

---

## 📂 Project Structure

The project follows a standard Maven structure with a clear separation of concerns:

```plaintext
AutomationExerciseWeb
├── src
│   ├── main
│   │   └── java
│   │       ├── api                     # API wrappers for backend operations
│   │       └── automationexercises
│   │           ├── drivers             # WebDriver initialization and management
│   │           ├── listeners           # TestNG listeners for logging and reporting
│   │           ├── pages               # Page Object Classes (Locators & Actions)
│   │           ├── utils               # Utilities (JSON Reader, TimeManager, etc.)
│   │           └── validations         # Custom assertion libraries
│   └── test
│       ├── java
│       │   └── automationexercises
│       │       └── tests
│       │           └── ui              # UI Test Classes (LoginTest, CartTest, etc.)
│       └── resources
│           └── test-data               # JSON data files for data-driven tests
├── testng.xml                          # Suite for running ALL UI tests
├── testng-smoke.xml                    # Suite for running SMOKE tests
├── pom.xml                             # Maven dependencies and build configuration
└── README.md                           # Project documentation
```

---

## 🚀 Getting Started

### Prerequisites
Ensure you have the following installed:
- **Java JDK 21** or higher
- **Maven** (added to system PATH)
- **Google Chrome** (latest version)

### Installation
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   ```
2. **Navigate to the project directory:**
   ```bash
   cd AutomationExerciseWeb
   ```
3. **Install dependencies:**
   ```bash
   mvn clean install -DskipTests
   ```

---

## 🧪 Running Tests

You can execute tests using Maven or directly via the TestNG XML files.

### 1️⃣ Run All Tests
Executes the full regression suite defined in `testng.xml`.
```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
```

### 2️⃣ Run Smoke Tests
Executes critical path scenarios (Login, Register, Checkout) defined in `testng-smoke.xml`.
```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng-smoke.xml
```

---

## 📊 Viewing Reports

After test execution, generate and view the Allure report:

1. **Generate and Serve Report:**
   This command will generate the report and automatically open it in your default browser.
   ```bash
   mvn allure:serve
   ```

2. **Generate Static Report:**
   To generate the report files in `target/site/allure-maven-plugin`:
   ```bash
   mvn allure:report
   ```

---

## 👩🏻‍💻 Author

**Aya Ali Mohamed**  
*Software Testing Engineer | ITI Graduate*

> This project was developed with ❤️ as ITI Graduation Project.