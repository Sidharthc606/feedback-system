package com.example.feedbacksystem.service;

import com.example.feedbacksystem.dto.FeedbackRequestDTO;
import com.example.feedbacksystem.dto.FeedbackResponseDTO;

import java.util.List;

/**
 * Service interface for handling feedback operations.
 *
 * <p>This interface defines methods for submitting and retrieving feedback,
 * allowing a clear separation between business logic and controller layers.</p>
 *
 * @author Sidharth Chaudhary
 */
public interface FeedbackService {

    /**
     * Submits a new feedback entry.
     *
     * @param feedbackRequestDTO the feedback request to be submitted
     * @return the saved feedback as a FeedbackDTO
     */
    FeedbackResponseDTO submitFeedback(FeedbackRequestDTO feedbackRequestDTO);

    /**
     * Retrieves all submitted feedback entries.
     *
     * @return a list of all feedbacks in the form of FeedbackDTOs
     */
    List<FeedbackResponseDTO> getAllFeedbacks();
}
