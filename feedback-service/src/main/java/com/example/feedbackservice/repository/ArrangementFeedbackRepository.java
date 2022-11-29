package com.example.feedbackservice.repository;

import com.example.feedbackservice.model.ArrangementFeedback;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementFeedbackRepository extends JpaRepository<ArrangementFeedback, Long> {
    List<ArrangementFeedback> findAllByArrangementId(Long arrangementId);
}
