package com.example.feedbacksystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a feedback entry submitted by a user.
 *
 * @author Sidharth Chaudhary
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    /**
     * Unique identifier for the feedback.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the user submitting feedback.
     */
    private String name;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * The feedback message provided by the user.
     */
    private String message;

    /**
     * Timestamp when the feedback was created.
     * Defaults to the current date and time.
     */
    private LocalDateTime createdAt = LocalDateTime.now();
}
