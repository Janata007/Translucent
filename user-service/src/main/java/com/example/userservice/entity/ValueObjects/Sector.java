package com.example.userservice.entity.ValueObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sector {
    private Long id;
    private String name;
    private String code;
}
