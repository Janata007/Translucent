package com.example.workservice.model.valueObjects;

import com.example.workservice.model.Task;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskWithFeedbackResponseTemplateVO {
    private Task task;
    private List<TaskFeedback> feedback;

}
