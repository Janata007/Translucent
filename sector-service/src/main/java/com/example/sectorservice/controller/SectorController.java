package com.example.sectorservice.controller;

import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.service.SectorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sector")
public class SectorController {
    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping("/")
    public Sector saveSector(@RequestBody Sector sector) {
        return sectorService.save(sector);
    }

    @GetMapping("/{id}")
    public Sector findSectorById(@PathVariable Long id) {
        return sectorService.findSectorById(id);
    }
}
