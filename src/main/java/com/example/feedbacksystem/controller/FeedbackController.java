package com.example.feedbacksystem.controller;

import com.example.feedbacksystem.dto.FeedbackRequestDTO;
import com.example.feedbacksystem.dto.FeedbackResponseDTO;
import com.example.feedbacksystem.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle feedback submission and retrieval.
 *
 * @author Sidharth Chaudhary
 */
@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    /**
     * Handles submission of feedback.
     *
     * @param feedbackRequestDTO the feedback request data
     * @return the submitted feedback with status 201 CREATED
     */
    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> submitFeedback(@Valid @RequestBody FeedbackRequestDTO feedbackRequestDTO){

        FeedbackResponseDTO feedbackResponseDTO = feedbackService.submitFeedback(feedbackRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResponseDTO);
    }

    /**
     * Retrieves all submitted feedbacks.
     *
     * @return list of all feedback responses
     */
    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedbacks(){
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }
}
