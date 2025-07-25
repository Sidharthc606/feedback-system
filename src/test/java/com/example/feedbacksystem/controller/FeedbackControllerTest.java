package com.example.feedbacksystem.controller;


import com.example.feedbacksystem.dto.FeedbackRequestDTO;
import com.example.feedbacksystem.dto.FeedbackResponseDTO;
import com.example.feedbacksystem.service.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FeedbackController.class)
class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private FeedbackService feedbackService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSubmitFeedbackReturnsCreated() throws Exception {
        FeedbackRequestDTO requestDTO = new FeedbackRequestDTO();
        requestDTO.setName("Sidharth");
        requestDTO.setEmail("sidharth@example.com");
        requestDTO.setMessage("Great service!");

        FeedbackResponseDTO responseDTO = new FeedbackResponseDTO();
        responseDTO.setName("Sidharth");
        responseDTO.setEmail("sidharth@example.com");
        responseDTO.setMessage("Great service!");

        Mockito.when(feedbackService.submitFeedback(any())).thenReturn(responseDTO);

        mockMvc.perform(post("/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Sidharth"))
                .andExpect(jsonPath("$.email").value("sidharth@example.com"))
                .andExpect(jsonPath("$.message").value("Great service!"));
    }
}
