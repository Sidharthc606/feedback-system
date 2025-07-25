package com.example.feedbacksystem.service;

import com.example.feedbacksystem.dto.FeedbackRequestDTO;
import com.example.feedbacksystem.dto.FeedbackResponseDTO;
import com.example.feedbacksystem.entity.Feedback;
import com.example.feedbacksystem.mapper.FeedbackMapper;
import com.example.feedbacksystem.repository.FeedbackRepository;
import com.example.feedbacksystem.service.impl.FeedbackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FeedbackServiceTest {
    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private FeedbackMapper feedbackMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFeedback() {
        FeedbackRequestDTO requestDTO = new FeedbackRequestDTO();
        requestDTO.setName("Sidharth");
        requestDTO.setEmail("sidharth@example.com");
        requestDTO.setMessage("Great service!");

        Feedback feedbackEntity = new Feedback();
        feedbackEntity.setName("Sidharth");
        feedbackEntity.setEmail("sidharth@example.com");
        feedbackEntity.setMessage("Great service!");

        Feedback savedFeedbackEntity = new Feedback();
        savedFeedbackEntity.setId(1L);
        savedFeedbackEntity.setName("Sidharth");
        savedFeedbackEntity.setEmail("sidharth@example.com");
        savedFeedbackEntity.setMessage("Great service!");

        FeedbackResponseDTO responseDTO = new FeedbackResponseDTO();
        responseDTO.setName("Sidharth");
        responseDTO.setEmail("sidharth@example.com");
        responseDTO.setMessage("Great service!");

        when(feedbackMapper.toEntity(requestDTO)).thenReturn(feedbackEntity);
        when(feedbackRepository.save(feedbackEntity)).thenReturn(savedFeedbackEntity);
        when(feedbackMapper.toDTO(savedFeedbackEntity)).thenReturn(responseDTO);

        FeedbackResponseDTO result = feedbackService.submitFeedback(requestDTO);

        assertNotNull(result);
        assertEquals("Sidharth", result.getName());
        assertEquals("sidharth@example.com", result.getEmail());
        assertEquals("Great service!", result.getMessage());

        verify(feedbackMapper).toEntity(requestDTO);
        verify(feedbackRepository).save(feedbackEntity);
        verify(feedbackMapper).toDTO(savedFeedbackEntity);
    }
}
