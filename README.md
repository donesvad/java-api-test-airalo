# Java API E2E Test Framework

## Disclaimer: This repository is based on the original open-source template repository, primarily adjusted to fit the provided API Automation Test exercise. You can find the original repository here: https://github.com/donesvad/java-api-test.

## Overview

This project is an end-to-end (E2E) test framework for API testing using Java. The framework leverages several powerful libraries and tools to provide
comprehensive testing capabilities for RESTful APIs and gRPC services.

## Key Features

- **Spring Boot Integration**: Utilizes Spring Boot for setting up test configurations and running tests.
- **JUnit 5**: Provides a robust testing environment using the JUnit 5 framework.
- **REST Assured**: Facilitates easy and powerful REST API testing.
- **JWT Handling**: Enables JWT token validation for secured endpoints.
- **Logback for Logging**: Leverages Spring's default Logback for flexible and performant logging capabilities.
- **Lombok**: Simplifies Java code by reducing boilerplate.
- **Allure Reporting**: Integrates with Allure for generating detailed test reports.
- **Test Parallelization**: Supports both thread-based and fork-based parallelization strategies to speed up test execution.
- **Automatic Retry of Failing Tests**: Automatically retries failing tests to handle flaky tests or transient failures.
- **Docker Support**: Provides Docker support for running tests in a containerized environment, ensuring consistent test execution across different machines.

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven** (version 3.6.0 or higher)
- **Allure CLI** (for generating and viewing reports)
- **Docker** (optional, for running tests in a containerized environment)

### Build and Run Tests

To build the project and run all tests, use the following Maven commands:

```bash
mvn clean install -DskipTests
mvn test
```

### Generating and Viewing Allure Reports

This project uses **Allure** to generate comprehensive and user-friendly test reports. Allure reports provide detailed insights into test execution, including
test results, logs, and visualizations.



#### How to Generate Allure Report Locally

To generate and view Allure reports locally, follow these steps:

1. **Navigate to the `target` directory:**
   ```bash
   cd target
   ```
2. Serve the Allure report:
   ```bash
   allure serve
   ```

This will start a local server and display the Allure report in your default web browser.

This command will start a local server and open the Allure report in your default web browser, allowing you to visualize the test results with detailed
insights.

Ensure that the Allure CLI is installed on your machine. If not, you can install it using the following commands:

```bash
brew install allure    # For macOS users using Homebrew
scoop install allure    # For Windows users using Scoop
```

Or follow the [installation instructions](https://allurereport.org/docs/install/) from the Allure documentation for other operating systems.

#### Viewing the Allure Report

This report includes:

- Test Results: Summary and details of passed, failed, and skipped tests.
- Test Suites: Breakdown of test suites and their execution results.
- Test History: Insights into test execution history and trends over time.
- Logs and Attachments: Detailed logs and any additional attachments captured during test execution.

### Logging

This application uses Logback, the default logging framework provided by Spring Boot. Logback offers a robust and flexible logging configuration.
You can customize log levels for specific packages or classes by modifying the `logback-test.xml` file located in the `src/test/resources` directory.

### Test Parallelization

To improve the efficiency and speed of the test execution, especially when dealing with a large number of test scenarios, this framework supports parallel
execution using both **thread-based** (JUnit 5) and **fork-based** (Maven Surefire plugin) parallelization strategies.

#### 1. Thread-Based Parallelization with JUnit 5

JUnit 5 natively supports parallel execution of tests using its configuration settings. You can run test methods or test classes concurrently, which helps speed
up the test suite execution.

**Configuration**
To enable thread-based parallelization with JUnit 5, you need to add the following configuration to your junit-platform.properties file, located in the
`src/test/resources` directory:

```properties
# junit-platform.properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.config.strategy=dynamic
junit.jupiter.execution.parallel.config.dynamic.factor=2
```

In this configuration:

- **junit.jupiter.execution.parallel.enabled**: Enables parallel execution.
- **junit.jupiter.execution.parallel.mode.default**: Sets the default parallel execution mode. Use concurrent to run test classes and methods in parallel.
- **junit.jupiter.execution.parallel.config.strategy**: Defines the parallel execution strategy. Options include fixed or dynamic.
- **junit.jupiter.execution.parallel.config.dynamic.factor**: For dynamic strategy, this factor is multiplied by the number of available processors to determine
  the maximum number of threads to use. For example, if you have 4 CPUs and a factor of 2, JUnit will use up to 8 threads.

#### 2. Fork-Based Parallelization with Maven Surefire Plugin

Fork-based parallelization runs multiple instances of the JVM, each executing a portion of the test suite. This method is more resource-intensive but provides a
higher degree of isolation between test cases, making it suitable for tests that have significant memory or CPU demands.

**Configuration**
To enable fork-based parallelization in Maven, update the surefire plugin configuration in your `pom.xml` file:

```xml
<configuration>
  <forkCount>2</forkCount>
  <reuseForks>true</reuseForks>
</configuration>
```

In this configuration:

- **forkCount**: Specifies the number of JVM instances to run in parallel. You can use a fixed number (e.g., 2) or a dynamic value based on available CPUs.
- **reuseForks**: When set to true, Maven reuses the JVM instances for subsequent tests, reducing the overhead of JVM startup time.

By combining both thread-based and fork-based parallelization strategies, you can optimize test execution time and resource utilization for your test suite.

### Automatic Retry of Failing Tests

To handle flaky tests or tests that intermittently fail due to non-deterministic issues (such as network timeouts or temporary service unavailability), this
framework supports automatic retries of failing tests using the `rerunFailingTestsCount` feature of the Maven Surefire and Failsafe plugins.

### Docker Support

To simplify the setup process and ensure a consistent environment across different machines, this project supports running the E2E API test framework inside a
Docker container. Docker allows you to package the framework along with all its dependencies, making it easier to run tests without worrying about local
environment configurations.
