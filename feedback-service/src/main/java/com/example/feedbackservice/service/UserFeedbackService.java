package com.example.feedbackservice.service;

import com.example.feedbackservice.model.UserFeedback;

public interface UserFeedbackService {
    UserFeedback getFeedbackForUser(Long userId);
    UserFeedback createFeedbackForUser(Long userId, UserFeedback feedback);
    double getUserTaskPercentage(Long userId);
    double getUserTaskGrade(Long userId);

}
