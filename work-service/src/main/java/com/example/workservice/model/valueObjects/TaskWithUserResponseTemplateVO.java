package com.example.workservice.model.valueObjects;

import com.example.workservice.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskWithUserResponseTemplateVO {
    private Task task;
    private AppUser appUser;

}
