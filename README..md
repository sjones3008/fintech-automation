
# Project Overview

This project demonstrates a comprehensive Quality Engineering automation strategy designed to validate core
user on boarding and financial transaction work flows. The suite incorporates both backend API testing and 
browser-based UI automation within a single repository, structured across separated, dedicated sub frameworks.

Key Features:  

1. REST API Automation Framework (Python + pytest)
   Target Services: Built for /api/users and /api/transactions microservices endpoints.  
   Test Coverage:
   *    CRUD Operations: Happy path validation for user registration and transaction processing.  
   *    Error & Schema Validation: Covers missing payload parameters, malformed email formats, negative/zero transfer amounts, and non-existent recipient IDs.  
   *    Auth & Security: Unauthenticated/unauthorized request headers testing (401/403 handling).  
   Architecture: Custom APIClient HTTP wrapper featuring request/response lifecycle logging and a dynamic DataFactory for payload generation.

2. Frontend UI Automation Framework (Java + Playwright)
  - Design Pattern:
      - Thread-safe Page Object Model (POM) architecture using Java 17 and Playwright Web-First Assertions.
  - Test Coverage:
      - User Onboarding Flow:
      - End-to-end user registration and inline validation messaging.  
  - Transaction Flow:
      - Financial transfer execution and form field error handling.
  - Cross-Browser & Execution:
      - Configured via TestNG (testng.xml) for multi-threaded parallel execution across Chromium, Firefox, and WebKit browsers.

3. Frontend UI Automation Framework (Java + Selenium)
  - Design Pattern:
      - Page Object Model (POM) architecture using Java 17 and Selenium Assertions.
  - Test Coverage:
      - Validating all the UI WebElements
  - Cross-Browser & Execution:
      - Configured via TestNG (testng.xml) for multi-threaded parallel execution across Chromium, Firefox, and WebKit browsers.

4. Reporting & Test Utilities.  
  - API Reporting:
     - Integrated pytest html providing structured test reports and file logs under api_reports/.
  - UI Visual Dashboards:
     - Integrated Allure Reports and ExtentReports with AspectJ Weaver bytecode weaving for step-by-step step logging and failure screenshot attachments.
  - Data & Environment Strategy:
     - Environment configurations (.env files for Python, static config loaders for Java) allowing seamless switching between local, staging and production environments.  

**Getting Started Prerequisites**
*  Python 3.9+ and pip
*  Java JDK 17+ and Apache Maven 3.8+
*  Node.js (optional, if running mock target servers locally)

# Running API Tests (pytest)
### Navigate to root directory
cd fintech-automation-framework
### Install Python dependencies
pip install -r requirements.txt
### Execute API test suite and generate HTML report
pytest --html=api_reports/report.html --self-contained-html

# Running UI Tests (Playwright + TestNG)
### Navigate to UI test module
cd ui_tests_playwright
### Execute TestNG suite via Maven
mvn clean test
### Generate and view interactive Allure Report
allure serve allure-results
