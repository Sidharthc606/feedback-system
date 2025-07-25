package com.example.feedbacksystem.advice;

import com.example.feedbacksystem.exception.FeedbackException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global exception handler for handling validation and custom exceptions across controllers.
 *
 * @author Sidharth Chaudhary
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors for request bodies with invalid fields.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> errorBody = createErrorBody(
                HttpStatus.BAD_REQUEST,
                "Validation Failed",
                "One or more fields have invalid values"
        );
        errorBody.put("path", request.getRequestURI());
        errorBody.put("validationErrors", validationErrors);

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles custom FeedbackException and returns a formatted error response.
     */
    @ExceptionHandler(FeedbackException.class)
    public ResponseEntity<Object> handleFeedbackException(FeedbackException ex, HttpServletRequest request) {
        Map<String, Object> errorBody = createErrorBody(
                HttpStatus.BAD_REQUEST,
                "Feedback Error",
                ex.getMessage()
        );
        errorBody.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all uncaught exceptions and returns a generic error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, HttpServletRequest request) {
        Map<String, Object> errorBody = createErrorBody(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                "An unexpected error occurred"
        );
        errorBody.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Creates a standard error response body with timestamp, status, error, and message.
     */
    private Map<String, Object> createErrorBody(HttpStatus status, String error, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return body;
    }
}
