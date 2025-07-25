package com.example.feedbacksystem.dto;

import lombok.Data;

/**
 * DTO for transferring feedback data without internal metadata like ID or timestamps.
 *
 * @author Sidharth Chaudhary
 */
@Data
public class FeedbackResponseDTO {

    private String name;

    private String email;

    private String message;
}
