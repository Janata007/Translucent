package com.example.sectorservice.controller;

import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.service.SectorService;
import org.springframework.web.bind.annotation.*;

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
    public Sector findSectorById(@PathVariable Long id){
        return sectorService.findSectorById(id);
    }
}
