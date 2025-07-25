package com.example.feedbacksystem.repository;

import com.example.feedbacksystem.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Feedback entities using SpringDataJPA.
 *
 * @author Sidharth Chaudhary
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

}
