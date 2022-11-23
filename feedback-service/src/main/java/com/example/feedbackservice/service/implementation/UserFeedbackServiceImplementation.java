package com.example.feedbackservice.service.implementation;

import com.example.feedbackservice.model.UserFeedback;
import com.example.feedbackservice.repository.UserFeedbackRepository;
import com.example.feedbackservice.service.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackServiceImplementation implements UserFeedbackService {
    @Autowired
    private UserFeedbackRepository userFeedbackRepository;

    @Override
    public UserFeedback getFeedbackForUser(Long userId) {
        return this.userFeedbackRepository.findById(userId).orElseThrow();
    }

    @Override
    public UserFeedback createFeedbackForUser(Long userId, UserFeedback feedback) {
        feedback.setUserForId(userId);
        return this.userFeedbackRepository.save(feedback);
    }
}
