package com.example.translucentfe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUser {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Long sectorId;
    private Long companyId;
    private String password;
    private String role;
    private boolean workVisible;
    private Long superiorId;
}
