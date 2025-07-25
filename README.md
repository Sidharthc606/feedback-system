📝 Feedback System
A simple Spring Boot application for submitting and managing user feedback. Built with Java 17+, Spring Boot 3.x, PostgreSQL/MySQL, JPA, MapStruct, and JUnit. Follows clean architecture, uses DTO-Entity separation, and supports dynamic DB configuration.

🛠️ Tech Stack
Java 17+

Spring Boot 3.x

Gradle (build tool)

PostgreSQL (default) / MySQL (optional)

Spring Data JPA

MapStruct

JUnit 5 + Mockito

Insomnia / cURL (for testing)

🚀 How to Run the Project
Prerequisites
Java 17+

Gradle installed (or use the Gradle wrapper)

PostgreSQL or MySQL running locally

Run with Gradle Wrapper
bash
Copy
Edit
./gradlew bootRun
Or build the JAR and run:
bash
Copy
Edit
./gradlew build -x test
java -jar build/libs/feedback-system-0.0.1-SNAPSHOT.jar
🧪 Running Tests
bash
Copy
Edit
./gradlew test
JUnit 5 and Mockito tests are located in src/test/java. Controller tests use @WebMvcTest.

📬 Sample API Requests
➕ Submit Feedback
Endpoint: POST /feedback

📥 cURL Example
bash
Copy
Edit
curl -X POST http://localhost:8080/feedback \
-H "Content-Type: application/json" \
-d '{
"name": "Sidharth",
"email": "Sidharth@example.com",
"message": "Great service!"
}'
📥 Insomnia Example
Method: POST

URL: http://localhost:8080/feedback

Body Type: JSON

Body:

json
Copy
Edit
{
"name": "Sidharth",
"email": "Sidharth@example.com",
"message": "Great service!"
}
📤 Sample Response
json
Copy
Edit
{
"name": "Sidharth",
"email": "Sidharth@example.com",
"message": "Great service!"
}
🧰 MapStruct Integration
MapStruct handles conversion between DTOs and entities automatically:

java
Copy
Edit
@Mapper(componentModel = "spring")
public interface FeedbackMapper {
Feedback toEntity(FeedbackRequestDTO dto);
FeedbackResponseDTO toDTO(Feedback entity);
}
No manual mapping logic is required.

🛢️ Database Configuration
✅ MySQL Support
MySQL supports DB auto-creation using the createDatabaseIfNotExist=true parameter.

⚠️ PostgreSQL Limitation
PostgreSQL does not support automatic DB creation. You must manually create the database:

bash
Copy
Edit
createdb feedback_db
💡 Custom Datasource Bean
Database configuration is done using a @Configuration class:

java
Copy
Edit
@Bean
@ConfigurationProperties("spring.datasource")
public DataSource dataSource() {
return DataSourceBuilder.create().build();
}
This enables flexible DB switching via application.properties or application.yml.

💥 Global Exception Handling
Custom exceptions are managed using a global handler:

java
Copy
Edit
@ControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(FeedbackException.class)
public ResponseEntity<String> handleFeedbackException(FeedbackException ex) {
return ResponseEntity.badRequest().body(ex.getMessage());
}
}
This ensures clean and consistent error messages in the API.

📁 Project Structure
arduino
Copy
Edit
src/
├── advice/
├── controller/
├── service/
├── mapper/
├── dto/
├── entity/
├── repository/
├── config/
├── exception/
└── FeedbackSystemApplication.java
👨‍💻 Author
Sidharth Chaudhary