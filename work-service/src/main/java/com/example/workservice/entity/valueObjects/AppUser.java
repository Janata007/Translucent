package com.example.workservice.entity.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Long sectorId;
}
