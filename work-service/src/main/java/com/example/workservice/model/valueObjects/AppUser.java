package com.example.workservice.model.valueObjects;

import com.example.workservice.AppUserRole;
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
    private AppUserRole role;
    private String lastName;
    private String email;
    private Long sectorId;
}
