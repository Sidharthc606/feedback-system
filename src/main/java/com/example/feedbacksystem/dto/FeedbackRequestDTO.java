package com.example.feedbacksystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for capturing feedback request details.
 *
 * @author Sidharth Chaudhary
 */
@Data
public class FeedbackRequestDTO {

    @NotBlank
    @Size(min = 3)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 10)
    private String message;
}
