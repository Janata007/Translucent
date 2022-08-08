package com.example.sectorservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sectorId;
    private String sectorName;
    private String sectorCode;

}
