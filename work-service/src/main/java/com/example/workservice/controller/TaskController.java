package com.example.workservice.controller;

import com.example.workservice.model.Task;
import com.example.workservice.model.valueObjects.TaskWithFeedbackResponseTemplateVO;
import com.example.workservice.model.valueObjects.TaskWithUserResponseTemplateVO;
import com.example.workservice.service.implementation.TaskServiceImplementation;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/work")
@Slf4j
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private TaskServiceImplementation taskService;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/{byUserId}/{forUserId}")
    public ResponseEntity<Task> saveTask(@RequestHeader("Authorization") String token, @RequestBody Task task,
                                         @PathVariable Long byUserId, @PathVariable Long forUserId) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        //todo: change createdForUser to be in body instead of path variable
        task.setCreatedByUserId(byUserId);
        task.setCreatedForUserId(forUserId);
        task.setDateCreated(LocalDateTime.now());
        Task saved = this.taskService.save(task);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(@RequestHeader("Authorization") String token) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(this.taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Task> removeTask(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity("Token not valid", HttpStatus.UNAUTHORIZED);
        }
        Task task = this.taskService.findById(id);
        Task removed = this.taskService.remove(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskWithUserResponseTemplateVO> getTaskWithUserForUser(
            @RequestHeader("Authorization") String token, @PathVariable("id") Long taskId) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity("Token not valid", HttpStatus.UNAUTHORIZED);
        }
        //the task created has the user listed as created FOR him
        TaskWithUserResponseTemplateVO returned = this.taskService.getTaskWithUserForUser(taskId);
        return new ResponseEntity<>(returned, HttpStatus.OK);
    }

    @GetMapping("/tasks/{userId}")
    public ResponseEntity<List<Task>> getTasksForUser(@RequestHeader("Authorization") String token,
                                                      @PathVariable Long userId) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity("Token not valid", HttpStatus.UNAUTHORIZED);
        }
        List<Task> returnList = this.taskService.getTasksForUser(userId);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @GetMapping("/{id}/feedback")
    public ResponseEntity<TaskWithFeedbackResponseTemplateVO> getTaskWithFeedbackList(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") Long taskId) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity("Token not valid", HttpStatus.UNAUTHORIZED);
        }
        TaskWithFeedbackResponseTemplateVO returnValue = this.taskService.getTaskWithFeedbacks(taskId);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PutMapping("/{id}/finished")
    public ResponseEntity<Task> setTaskToFinished(@RequestHeader("Authorization") String token,
                                                  @PathVariable("id") Long id) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity("Token not valid", HttpStatus.UNAUTHORIZED);
        }
        Task task = this.taskService.findById(id);
        task.setFinished(true);
        Task saved = this.taskService.save(task);
        return new ResponseEntity<>(saved, org.springframework.http.HttpStatus.OK);
    }
    @PutMapping("/{id}/accept")
    public ResponseEntity<Task> acceptTask(@RequestHeader("Authorization") String token,
                                           @PathVariable("id") Long id) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity("Token not valid", HttpStatus.UNAUTHORIZED);
        }
        Task task = this.taskService.findById(id);
        task.setAccepted(true);
        Task saved = this.taskService.save(task);
        return new ResponseEntity<>(saved, org.springframework.http.HttpStatus.OK);
    }

    @PutMapping("/{id}/unfinished")
    public ResponseEntity<Task> setTaskToUnfinished(@RequestHeader("Authorization") String token,
                                                    @PathVariable("id") Long id) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity("Token not valid", HttpStatus.UNAUTHORIZED);
        }
        Task task = this.taskService.findById(id);
        task.setFinished(false);
        Task saved = this.taskService.save(task);
        return new ResponseEntity<>(saved, org.springframework.http.HttpStatus.OK);
    }

    public Boolean validateToken(String token) throws Exception {
        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String payloadChunks[] = payload.split(":|,");
        String username = "";
        try {
            username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.substring(7)).getBody().getSubject();
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
}