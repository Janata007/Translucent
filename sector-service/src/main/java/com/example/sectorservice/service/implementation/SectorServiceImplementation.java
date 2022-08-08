package com.example.sectorservice.service.implementation;

import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.repository.SectorRepository;
import com.example.sectorservice.service.SectorService;
import org.springframework.stereotype.Service;

@Service
public class SectorServiceImplementation implements SectorService {
    private final SectorRepository sectorRepository;

    public SectorServiceImplementation(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public Sector save(Sector sector) {
        return sectorRepository.save(sector);
    }

    @Override
    public Sector findSectorById(Long id) {
        return sectorRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find sector"));
    }
}
