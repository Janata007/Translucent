package com.example.workservice.service;

import com.example.workservice.model.Task;
import com.example.workservice.model.valueObjects.TaskWithFeedbackResponseTemplateVO;
import com.example.workservice.model.valueObjects.TaskWithUserResponseTemplateVO;
import java.util.List;

public interface TaskService {
    Task save(Task task);

    Task remove(Task task);

    Task findById(Long id);

    TaskWithUserResponseTemplateVO getTaskWithUserForUser(Long taskId);
    TaskWithFeedbackResponseTemplateVO getTaskWithFeedbacks(Long taskId);
    List<Task> getTasksForUser(Long userId);
    List<Task> getAllTasks();
}
