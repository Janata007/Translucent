package com.example.userservice.entity.ValueObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewUserRequestTemplateVO {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Long sectorId;
    private Long companyId;
    private Long superiorId;
    private String password;
}
