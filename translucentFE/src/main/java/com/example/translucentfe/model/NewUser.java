package com.example.translucentfe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUser {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private int sectorId;
    private String password;
}
