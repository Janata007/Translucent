package com.example.feedbackservice.service.implementation;

import com.example.feedbackservice.model.UserFeedback;
import com.example.feedbackservice.repository.UserFeedbackRepository;
import com.example.feedbackservice.service.UserFeedbackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackServiceImplementation implements UserFeedbackService {
    @Autowired
    private UserFeedbackRepository userFeedbackRepository;
    @Autowired
    private TaskFeedbackServiceImplementation taskFeedbackService;

    @Override
    public List<UserFeedback> getFeedbacksForUser(Long userId) {
        return this.userFeedbackRepository.findAllByUserForId(userId);
    }

    @Override
    public List<UserFeedback> getFeedbacksFromUser(Long userId) {
        return this.userFeedbackRepository.findAllByUserFromId(userId);
    }

    @Override
    public UserFeedback createFeedbackForUser(Long userId, UserFeedback feedback) {
        feedback.setUserForId(userId);
        return this.userFeedbackRepository.save(feedback);
    }

    @Override
    public double getUserTaskPercentage(Long userId) {
        return 0;
    }

    @Override
    public double getUserTaskGrade(Long userId) {
        return 0;
    }
}
