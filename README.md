Got it\! Removing the `variables.env` content from the README. Here's the updated version:

-----

# 🚀 Feedback System: A Spring Boot Microservice

A robust and simple Spring Boot application designed for submitting and managing user feedback. This project exemplifies modern Java development practices, featuring clean architecture, clear DTO-Entity separation, and flexible database configuration.

-----

## ✨ Features

* **Feedback Submission:** REST API for users to submit feedback.
* **Clean Architecture:** Structured codebase for maintainability and scalability.
* **DTO-Entity Separation:** Clear distinction between data transfer objects and persistence entities.
* **Dynamic Database Configuration:** Easily switch between PostgreSQL (default) and MySQL.
* **Automatic Mapping:** Seamless DTO-Entity conversion with MapStruct.
* **Robust Error Handling:** Global exception handling for consistent API responses.
* **Comprehensive Testing:** Unit and integration tests with JUnit 5 and Mockito.

-----

## 🛠️ Tech Stack

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Build Tool:** Gradle
* **Databases:**
    * PostgreSQL (Default)
    * MySQL (Optional)
* **ORM:** Spring Data JPA / Hibernate
* **Mapping:** MapStruct
* **Testing:** JUnit 5, Mockito, `@WebMvcTest`
* **API Testing Tools:** Insomnia / cURL

-----

## 🚀 Getting Started

### Prerequisites

Before you run the project, ensure you have the following installed:

* **Java 17+** (JDK)
* **Gradle** (or rely on the included Gradle Wrapper)
* A running instance of **PostgreSQL** or **MySQL** database server.

### Running the Application

1.  **Clone the Repository:**

    ```bash
    git clone https://github.com/Sidharthc606/feedback-system.git
    cd feedback-system
    ```

2.  **Database Setup:**

    * **PostgreSQL:** Create the database manually:
      ```bash
      createdb feedback_db
      ```
    * **MySQL:** The application can attempt to auto-create the database if configured correctly in `application.properties` with `createDatabaseIfNotExist=true` (for development). Ensure your MySQL user has `CREATE` database privileges.

3.  **Configure Database Credentials:**
    Create a `variables.env` file in the project root to store your database credentials. **Do NOT commit this file to Git\!** It's already included in `.gitignore`.

    * **IntelliJ IDEA Users:** Install the "EnvFile" plugin (`File > Settings > Plugins`) and configure your Spring Boot Run Configuration to load `variables.env`.
    * **For Production/Deployment:** Sensitive variables should be passed via system environment variables or a dedicated secrets management solution.

4.  **Build and Run:**

    * **Using Gradle Wrapper:**
      ```bash
      ./gradlew bootRun
      ```
    * **Building the JAR and Running:**
      ```bash
      ./gradlew build -x test # -x test skips running tests during build
      java -jar build/libs/feedback-system-0.0.1-SNAPSHOT.jar
      ```

-----

## 🧪 Running Tests

Execute all JUnit 5 and Mockito tests:

```bash
./gradlew test
```

* Unit tests are located in `src/test/java`.
* Controller layer tests leverage `@WebMvcTest` for focused testing.

-----

## 📬 Sample API Requests

The application exposes a REST endpoint for feedback submission.

### ➕ Submit Feedback

**Endpoint:** `POST /feedback`

**Content-Type:** `application/json`

**📥 cURL Example:**

```bash
curl -X POST http://localhost:8080/feedback \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Sidharth Chaudhary",
    "email": "sidharth.chaudhary@example.com",
    "message": "This is an amazing feedback system! Keep up the great work."
}'
```

**📥 Insomnia Example:**

* **Method:** `POST`
* **URL:** `http://localhost:8080/feedback`
* **Body Type:** `JSON`
* **Body:**
  ```json
  {
    "name": "Sidharth Chaudhary",
    "email": "sidharth.chaudhary@example.com",
    "message": "This is an amazing feedback system! Keep up the great work."
  }
  ```

**📤 Sample Response (Success):**

```json
{
  "id": 1,
  "name": "Sidharth Chaudhary",
  "email": "sidharth.chaudhary@example.com",
  "message": "This is an amazing feedback system! Keep up the great work."
}
```

-----

## ⚙️ Core Components

### 🧰 MapStruct Integration

MapStruct is used for efficient and boilerplate-free conversion between DTOs and entities.

```java
@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    Feedback toEntity(FeedbackRequestDTO dto);
    FeedbackResponseDTO toDTO(Feedback entity);
}
```

No manual mapping logic is required, ensuring clean and concise data transfer.

### 🛢️ Dynamic Database Configuration

The project supports flexible database integration, primarily configured via `application.properties` (or `application.yml`) and environment variables.

* **MySQL Support:**
  MySQL's JDBC driver supports automatic database creation (convenient for development) via the URL parameter:

  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/feedback_db?useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true
  ```

* **PostgreSQL Limitation:**
  PostgreSQL does **not** support automatic database creation through the JDBC URL. You must manually create the database before running the application:

  ```bash
  createdb feedback_db
  ```

* **Custom Datasource Bean:**
  Database configuration is dynamically built using a `@Configuration` class and `@ConfigurationProperties`, allowing for easy switching of database providers via externalized properties.

  ```java
  @Configuration
  public class DataSourceConfig { // Or similar name if it's within another config class
      @Bean
      @ConfigurationProperties("spring.datasource")
      public DataSource dataSource() {
          return DataSourceBuilder.create().build();
      }
  }
  ```

### 💥 Global Exception Handling

Custom application-specific exceptions and general errors are gracefully handled using a `@ControllerAdvice` for consistent API responses.

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FeedbackException.class)
    public ResponseEntity<String> handleFeedbackException(FeedbackException ex) {
        // More specific error handling might include a custom error DTO
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    // Add other exception handlers as needed (e.g., for validation errors)
}
```

This ensures a predictable and user-friendly error reporting mechanism for the API.

-----

## 📁 Project Structure

```
src/
├── main/
│   └── java/com/example/feedbacksystem/
│       ├── controller/        # REST API endpoints
│       ├── service/           # Business logic
│       ├── mapper/            # MapStruct interfaces for DTO-Entity conversion
│       ├── dto/               # Data Transfer Objects for API requests/responses
│       ├── entity/            # JPA Entities for database mapping
│       ├── repository/        # Spring Data JPA repositories for data access
│       ├── config/            # Application-wide configurations (e.g., DataSource)
│       ├── exception/         # Custom exception classes
│       ├── advice/            # Global exception handling via @ControllerAdvice
│       └── FeedbackSystemApplication.java # Main Spring Boot application class
└── test/
    └── java/com/example/feedbacksystem/
        ├── controller/        # Tests for controllers (@WebMvcTest)
        ├── service/           # Tests for service layer
        └── repository/        # Tests for repository layer
```

-----

## 👨‍💻 Author

**Sidharth Chaudhary**

-----