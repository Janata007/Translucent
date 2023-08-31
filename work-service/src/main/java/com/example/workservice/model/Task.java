package com.example.workservice.model;

import com.example.workservice.Priority;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Priority priority;
    private String description;
    private boolean finished;
    private Long arrangementId;
    private Long createdByUserId;
    private Long createdForUserId;
    private LocalDateTime dateDue;
    private boolean accepted;

}
