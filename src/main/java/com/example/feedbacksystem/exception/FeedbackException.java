package com.example.feedbacksystem.exception;

/**
 * Custom exception for handling feedback-related errors.
 *
 * @author Sidharth Chaudhary
 */
public class FeedbackException extends RuntimeException{
    public FeedbackException(String message) {
        super(message);
    }
}
