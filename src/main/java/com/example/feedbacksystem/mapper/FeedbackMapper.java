package com.example.feedbacksystem.mapper;

import com.example.feedbacksystem.dto.FeedbackRequestDTO;
import com.example.feedbacksystem.dto.FeedbackResponseDTO;
import com.example.feedbacksystem.entity.Feedback;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for converting between Feedback entities and DTOs.
 *
 * @author Sidharth Chaudhary
 */
@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackResponseDTO toDTO(Feedback feedback);

    Feedback toEntity(FeedbackRequestDTO feedbackRequestDTO);
}
