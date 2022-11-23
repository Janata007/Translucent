package com.example.workservice.entity.valueObjects;

import com.example.workservice.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Task task;
    private AppUser appUser;

}
