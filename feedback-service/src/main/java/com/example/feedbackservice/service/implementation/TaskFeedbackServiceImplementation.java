package com.example.feedbackservice.service.implementation;

import com.example.feedbackservice.model.TaskFeedback;
import com.example.feedbackservice.repository.TaskFeedbackRepository;
import com.example.feedbackservice.service.TaskFeedbackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskFeedbackServiceImplementation implements TaskFeedbackService {
    @Autowired
    private TaskFeedbackRepository taskFeedbackRepository;

    @Override
    public List<TaskFeedback> getFeedbacksForTask(Long taskId) {
        return this.taskFeedbackRepository.findAllByTaskId(taskId);
    }

    @Override
    public TaskFeedback createFeedbackForTask(Long taskId, TaskFeedback feedback) {
        feedback.setTaskId(taskId);
        return this.taskFeedbackRepository.save(feedback);
    }
}
