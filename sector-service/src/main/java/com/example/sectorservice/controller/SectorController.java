package com.example.sectorservice.controller;

import com.example.sectorservice.entity.JwtRequest;
import com.example.sectorservice.entity.JwtResponse;
import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.service.implementation.SectorServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sector")
public class SectorController {
    @Autowired
    private SectorServiceImplementation sectorService;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/")
    public Sector saveSector(@RequestHeader("Authorization") String token, @RequestBody Sector sector) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return sectorService.save(sector);
    }

    @GetMapping("/{id}")
    public Sector findById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return sectorService.findById(id);
    }

    @GetMapping("/services/{id}")
    public List<OfferedService> getOfferedServicesForSector(@RequestHeader("Authorization") String token,
                                                            @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return sectorService.getOfferedServicesForSector(id);
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestHeader("Authorization") String token, @RequestBody JwtRequest jwtRequest)
        throws Exception {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        //todo: redirect to user service auth
        return new JwtResponse("token");
    }
}
