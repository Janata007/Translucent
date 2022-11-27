package com.example.workservice.service;

import com.example.workservice.model.Task;
import com.example.workservice.model.valueObjects.TaskWithUserResponseTemplateVO;

public interface TaskService {
    Task save(Task task);

    Task remove(Task task);

    Task findById(Long id);

    TaskWithUserResponseTemplateVO getTaskWithUserForUser(Long taskId);
}
