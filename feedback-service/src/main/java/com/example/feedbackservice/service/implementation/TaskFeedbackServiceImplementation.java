package com.example.feedbackservice.service.implementation;

import com.example.feedbackservice.model.TaskFeedback;
import com.example.feedbackservice.repository.TaskFeedbackRepository;
import com.example.feedbackservice.service.TaskFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskFeedbackServiceImplementation implements TaskFeedbackService {
    @Autowired
    private TaskFeedbackRepository taskFeedbackRepository;

    @Override
    public TaskFeedback getFeedbackForTask(Long taskId) {
        return this.taskFeedbackRepository.findById(taskId).orElseThrow();
    }
}
