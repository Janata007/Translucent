package com.example.userservice.entity.ValueObjects;

import com.example.userservice.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private AppUser appUser;
    private Sector sector;
}
