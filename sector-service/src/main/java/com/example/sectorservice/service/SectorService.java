package com.example.sectorservice.service;

import com.example.sectorservice.entity.JwtRequest;
import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.entity.Sector;
import java.util.List;

public interface SectorService {
    Sector save(Sector sector);

    Sector findById(Long id);

    List<OfferedService> getOfferedServicesForSector(Long id);
    List<Sector> getAllSectors();
    Boolean validateToken(String token, String secretKey) throws Exception;
    String authenticateUser(JwtRequest request);
}
