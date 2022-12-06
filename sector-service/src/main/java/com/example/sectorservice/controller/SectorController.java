package com.example.sectorservice.controller;

import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.service.implementation.SectorServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sector")
public class SectorController {
    @Autowired
    private SectorServiceImplementation sectorService;

    @PostMapping("/")
    public Sector saveSector(@RequestBody Sector sector) {
        return sectorService.save(sector);
    }

    @GetMapping("/{id}")
    public Sector findById(@PathVariable Long id) {
        return sectorService.findById(id);
    }

    @GetMapping("/services/{id}")
    public List<OfferedService> getOfferedServicesForSector(@PathVariable Long id) {
        return sectorService.getOfferedServicesForSector(id);
    }
}
