package com.example.workservice.service;

import com.example.workservice.entity.Task;
import com.example.workservice.entity.valueObjects.ResponseTemplateVO;

public interface TaskService {
    Task save(Task task);
    Task remove(Task task);
    Task findById(Long id);
    ResponseTemplateVO getTaskWithUser(Long taskId);
}
