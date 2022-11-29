package com.example.workservice.model.valueObjects;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskFeedbackList {
    List<TaskFeedback> feedbackList;
}
