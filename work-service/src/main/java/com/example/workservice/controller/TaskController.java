package com.example.workservice.controller;

import com.example.workservice.entity.Task;
import com.example.workservice.service.implementation.TaskServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work")
public class TaskController {
    @Autowired
    private TaskServiceImplementation taskService;

    @PostMapping("/")
    public Task saveTask(@RequestBody Task task) {
        return this.taskService.save(task);
    }

    @PostMapping("/remove/{id}")
    public Task removeTask(@PathVariable Long id) {
        Task task = this.taskService.findById(id);
        return this.taskService.remove(task);
    }
}
