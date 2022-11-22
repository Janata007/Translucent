package com.example.workservice.service;

import com.example.workservice.entity.Task;

public interface TaskService {
    Task save(Task task);
    Task remove(Task task);
    Task findById(Long id);
}
