package com.example.translucentfe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithSectorVO {
    private NewUser user;
    private Sector sector;

}
