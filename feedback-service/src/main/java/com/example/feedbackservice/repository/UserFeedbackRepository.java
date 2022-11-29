package com.example.feedbackservice.repository;

import com.example.feedbackservice.model.UserFeedback;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Long> {
    List<UserFeedback> findAllByUserForId(Long userId);
    List<UserFeedback> findAllByUserFromId(Long userId);
}
