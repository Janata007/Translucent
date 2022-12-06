package com.example.feedbackservice.service;

import com.example.feedbackservice.model.UserFeedback;
import java.util.List;

public interface UserFeedbackService {
    List<UserFeedback> getFeedbacksForUser(Long userId);
    List<UserFeedback> getFeedbacksFromUser(Long userId);

    UserFeedback createFeedbackForUser(UserFeedback feedback);

    double getUserTaskPercentage(Long userId);

    double getUserTaskGrade(Long userId);

}
