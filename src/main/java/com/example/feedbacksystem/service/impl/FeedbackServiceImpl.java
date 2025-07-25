package com.example.feedbacksystem.service.impl;

import com.example.feedbacksystem.dto.FeedbackRequestDTO;
import com.example.feedbacksystem.dto.FeedbackResponseDTO;
import com.example.feedbacksystem.entity.Feedback;
import com.example.feedbacksystem.exception.FeedbackException;
import com.example.feedbacksystem.mapper.FeedbackMapper;
import com.example.feedbacksystem.repository.FeedbackRepository;
import com.example.feedbacksystem.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of FeedbackService for handling feedback submission and retrieval.
 *
 * @author Sidharth Chaudhary
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public FeedbackResponseDTO submitFeedback(FeedbackRequestDTO feedbackRequestDTO) {
        try{
            Feedback feedback = feedbackMapper.toEntity(feedbackRequestDTO);
            log.info("Saving feedback for email : {}",feedback.getEmail());
            Feedback saved = feedbackRepository.save(feedback);
            return feedbackMapper.toDTO(saved);
        } catch (Exception e) {
            throw new FeedbackException("Unable to submit feedback");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeedbackResponseDTO> getAllFeedbacks() {
        try{
            log.info("Fetching feedbacks");
            return feedbackRepository.findAll()
                    .stream()
                    .map(feedbackMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new FeedbackException("Failed to fetch feedbacks");
        }
    }
}
