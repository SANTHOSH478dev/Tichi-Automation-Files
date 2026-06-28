# 🚀 Tichi Automation Framework

This project is a **Selenium WebDriver + TestNG automation framework** developed to test the login and sign-up functionality of the **Tichi web application**.  
It follows the **Page Object Model (POM)** design pattern and uses Maven for build management.

---

## 📌 Project Objective

The main objective of this project is to automate and validate different login and sign-up scenarios such as:

- Valid login and registration
- Invalid email / empty field submissions
- Password masking and show/hide toggle
- Security tests (SQL injection)
- Navigation flows (Forgot Password, Sign Up link)

This helps ensure the application behaves correctly under different user inputs.

---

## 🛠️ Tech Stack

- Java (Programming Language)
- Selenium WebDriver (UI Automation)
- TestNG (Test Execution Framework)
- Maven (Build Tool)
- Allure Reports (HTML Reporting)
- Git & GitHub (Version Control)

---

## 📁 Project Structure

```
Tichi-Automation/
│
├── src/main/java
│   ├── base/
│   │   └── BaseTest.java        → @BeforeMethod / @AfterMethod (driver setup & teardown)
│   ├── pages/
│   │   ├── LoginPage.java       → Page Object for Login page
│   │   └── SignUpPage.java      → Page Object for Sign Up page
│   └── utils/
│       ├── ConfigReader.java    → Reads config.properties
│       └── DriverFactory.java   → ThreadLocal WebDriver management
│
├── src/test/java
│   ├── tests/
│   │   ├── LoginTest.java       → 14 login test cases (TC-LG-01 to TC-LG-14)
│   │   └── SignUpTest.java      → 8 sign-up test cases (TC-SU-01 to TC-SU-08)
│   └── listeners/
│       └── TestListener.java    → Pass/Fail/Skip console logging
│
├── src/test/resources/
│   ├── config.properties        → App URL and test credentials
│   └── testng.xml               → TestNG suite configuration
│
├── reports/                     → Generated HTML execution reports
├── Tichi_QA_Final_Report.xlsx   → Final QA test case & defect report
├── pom.xml                      → Maven dependencies
└── README.md
```

---

## 🧪 Test Scenarios Covered

### Sign Up Test Cases (TC-SU-01 to TC-SU-08)

| TC ID | Scenario | Status |
|-------|----------|--------|
| TC-SU-01 | Successful Registration | ✅ Pass |
| TC-SU-02 | Registration with Already Registered Email | ✅ Pass |
| TC-SU-03 | Registration with Invalid Email Format | ✅ Pass |
| TC-SU-04 | Registration with All Fields Empty | ✅ Pass |
| TC-SU-05 | Registration with Weak Password | ✅ Pass |
| TC-SU-06 | Password and Confirm Password Mismatch | ✅ Pass |
| TC-SU-07 | Name Field Accepts Spaces Only | ✅ Pass |
| TC-SU-08 | Password Field Masking | ✅ Pass |

### Login Test Cases (TC-LG-01 to TC-LG-14)

| TC ID | Scenario | Status |
|-------|----------|--------|
| TC-LG-01 | Successful Login with Valid Credentials | ✅ Pass |
| TC-LG-02 | Login with Incorrect Password | ✅ Pass |
| TC-LG-03 | Login with Unregistered Email | ✅ Pass |
| TC-LG-04 | Login with Both Fields Empty | ✅ Pass |
| TC-LG-05 | Login with Invalid Email Format | ❌ Fail — BUG DR-001 |
| TC-LG-06 | Login with Empty Password | ✅ Pass |
| TC-LG-07 | Login with Empty Email | ✅ Pass |
| TC-LG-08 | Password Field Masking on Login Page | ✅ Pass |
| TC-LG-09 | Show / Hide Password Toggle | ✅ Pass |
| TC-LG-10 | Forgot Password Link Navigation | ✅ Pass |
| TC-LG-11 | Navigate to Sign Up from Login Page | ✅ Pass |
| TC-LG-12 | Case-Insensitive Email Login | ✅ Pass |
| TC-LG-13 | SQL Injection Attempt in Login Fields | ✅ Pass |
| TC-LG-14 | Successful Logout and Re-Login | ✅ Pass |

**Total: 22 Test Cases | ✅ Pass: 21 | ❌ Fail: 1 | 🐛 Bugs Found: 1**

---

## 🐛 Defect Summary

| Defect ID | Title | Severity | Priority | Status |
|-----------|-------|----------|----------|--------|
| DR-001 | Application allows login with invalid email format | High | High | Open |

### DR-001 — Details
- **Module:** Login Page  
- **Environment:** Staging | Chrome | Windows 11  
- **URL:** https://tichi-app-webapp-stage.web.app  
- **Description:** The Login page does not validate email format using custom app-level validation. Inputs like `userexample` or `@example.com` only trigger a browser-native HTML5 tooltip — no custom inline error UI is shown.  
- **Expected:** Custom inline error: `Please enter a valid email address.` — login request blocked.  
- **Actual:** Only browser-native HTML5 tooltip. Zero app-level validation errors in DevTools Console.  
- **Suggested Fix:** Add JS regex validation on submit and render a styled inline error matching the app design system.

---

## 📊 QA Report

| File | Description |
|------|-------------|
| `Tichi_QA_Final_Report.xlsx` | Final QA report — 22 test cases, defect report DR-001, screenshot evidence |

---

## ⚙️ Setup Instructions

### 1. Clone Repository
```bash
git clone 
```

### 2. Navigate to Project
```bash
cd Tichi-Automation
```

### 3. Install Dependencies
```bash
mvn clean install
```

### 4. Run Tests
```bash
mvn clean test
```

---

## 📊 Execution Reports

After running, Allure reports are generated at:

```
target/allure-results/
```

To view the report:
```bash
mvn allure:serve
```

---

## 📌 Key Features

✔ Page Object Model (POM) structure  
✔ ThreadLocal WebDriver (parallel-safe)  
✔ Reusable BaseTest with @BeforeMethod / @AfterMethod  
✔ Dynamic CSS locators with Explicit Waits  
✔ TestNG listener for Pass/Fail/Skip logging  
✔ Allure HTML reporting  
✔ Maven project structure  
✔ Comprehensive manual QA documentation included  
✔ Detailed defect report DR-001 with reproduction steps and evidence  

---

## 🚀 How to Run Tests

```bash
mvn clean test
```

---

## 👨‍💻 Author

**Nithish**  
QA Automation Engineer (Aspiring)  
QA Final Report by: **Santhoshkumar M** — v1.0 | 28 June 2026

---

## ⭐ Future Improvements

- Add screenshot capture on test failure
- Add CI/CD with GitHub Actions
- Add parallel execution support
- Implement custom email validation fix for DR-001
- Add more test scenarios (profile, password reset, etc.)

---

## 📌 Note

This project is designed for QA Internship assignment submission and demonstrates real-world automation framework design using industry best practices.  
The `Tichi_QA_Final_Report.xlsx` contains the complete manual test case documentation and defect report with evidence screenshots.
