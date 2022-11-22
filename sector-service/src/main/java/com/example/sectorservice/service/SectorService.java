package com.example.sectorservice.service;

import com.example.sectorservice.entity.Sector;

public interface SectorService {
    Sector save(Sector sector);

    Sector findById(Long id);
}
