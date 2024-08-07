package com.example.sectorservice.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    private List<Sector> sectorList = new ArrayList<>();

    public void removeSector(Sector sector) {
                sectorList.remove(sector);
    }

    public void addSector(Sector sector) {
        boolean isPresent = false;
        for (Sector s : sectorList) {
            if (s.equals(sector)) {
                isPresent = true;
            }
        }
        if (!isPresent) {
            sectorList.add(sector);
        }
    }
}
