package com.example.feedbackservice.repository;

import com.example.feedbackservice.model.TaskFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskFeedbackRepository extends JpaRepository<TaskFeedback, Long> {
}
