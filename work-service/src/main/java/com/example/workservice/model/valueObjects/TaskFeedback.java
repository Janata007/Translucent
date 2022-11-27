package com.example.workservice.model.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskFeedback {
    private Long id;
    private String description;
    private double percent;
    private double grade;
    private Long userFromId;
}
