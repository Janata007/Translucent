package com.example.workservice.controller;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import com.example.workservice.model.Task;
import com.example.workservice.model.valueObjects.TaskWithFeedbackResponseTemplateVO;
import com.example.workservice.model.valueObjects.TaskWithUserResponseTemplateVO;
import com.example.workservice.service.implementation.TaskServiceImplementation;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import java.util.Base64;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work")
public class TaskController {
    @Autowired
    private TaskServiceImplementation taskService;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/{byUserId}/{forUserId}")
    public Task saveTask(@RequestHeader("Authorization") String token, @RequestBody Task task,
                         @PathVariable Long byUserId, @PathVariable Long forUserId) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
        }
        //todo: change createdForUser to be in body instead of path variable
        task.setCreatedByUser(byUserId);
        task.setCreatedForUser(forUserId);
        return this.taskService.save(task);
    }

    @PostMapping("/remove/{id}")
    public Task removeTask(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
        }
        Task task = this.taskService.findById(id);
        return this.taskService.remove(task);
    }

    @GetMapping("/{id}")
    public TaskWithUserResponseTemplateVO getTaskWithUserForUser(@RequestHeader("Authorization") String token,
                                                                 @PathVariable("id") Long taskId) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
        }
        //the task created has the user listed as created FOR him
        return this.taskService.getTaskWithUserForUser(taskId);
    }

    @GetMapping("/tasks/{userId}")
    public List<Task> getTasksForUser(@RequestHeader("Authorization") String token, @PathVariable Long userId) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
        }
        return this.taskService.getTasksForUser(userId);
    }

    @GetMapping("/{id}/feedback")
    public TaskWithFeedbackResponseTemplateVO getTaskWithFeedbackList(@RequestHeader("Authorization") String token,
                                                                      @PathVariable("id") Long taskId) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
        }
        return this.taskService.getTaskWithFeedbacks(taskId);
    }

    @PutMapping("/{id}/finished")
    public Task setTaskToFinished(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {
        try {
            this.validateToken(token);
        } catch (Exception e) {
        }
        Task task = this.taskService.findById(id);
        task.setFinished(true);
        return this.taskService.save(task);
    }

    protected Boolean validateToken(String token) throws Exception {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        SignatureAlgorithm sa = HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new Exception("Could not verify JWT token integrity!");
        }
        return true;
    }
}