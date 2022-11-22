package com.example.workservice.service.implementation;

import com.example.workservice.entity.Task;
import com.example.workservice.repository.TaskRepository;
import com.example.workservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImplementation implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return this.taskRepository.save(task);
    }

    @Override
    public Task remove(Task task) {
        this.taskRepository.delete(task);
        return task;
    }

    @Override
    public Task findById(Long id) {
        return this.taskRepository.findById(id).orElseThrow();
    }
}
