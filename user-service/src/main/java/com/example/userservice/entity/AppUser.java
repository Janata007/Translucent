package com.example.userservice.entity;

import com.example.userservice.AppUserRole;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Long sectorId;
    private String password;
    private AppUserRole role;
    private boolean workVisible;
    private Long superiorId;
    @ManyToMany
    private List<Arrangement> arrangements;
}
