package com.example.userservice.entity.ValueObjects;

import com.example.userservice.Priority;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewArrangementRequestTemplateVO {
    private String name;
    private String code;
    private double duration;
    private LocalDateTime startTime; //yyyy-MM-dd-HH-mm-ss-ns format
    private LocalDateTime endTime;
    private Priority priority;
    private List<Long> participants;
}
