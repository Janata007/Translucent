package com.example.translucentfe.model;

import java.util.List;
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
    private List<String> offeredServices;
}
