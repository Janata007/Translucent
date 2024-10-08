package com.example.sectorservice.controller;

import com.example.sectorservice.entity.*;
import com.example.sectorservice.service.implementation.CompanyServiceImplementation;
import com.example.sectorservice.service.implementation.SectorServiceImplementation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sector")
@Slf4j
@CrossOrigin
public class SectorController {
    @Autowired
    private SectorServiceImplementation sectorService;
    @Autowired
    private CompanyServiceImplementation companyService;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/")
    public ResponseEntity<Sector> saveSector(@RequestHeader("Authorization") String token, @RequestBody Sector sector) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        for(Sector s: this.sectorService.getAllSectors()){
            if(s.getName().equals(sector.getName()) && s.getCode().equals(sector.getCode())){
                return null;
            }
        }
        Sector saved = this.sectorService.save(sector);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sector> findById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        log.info("Finding sector by id..");
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Sector found = this.sectorService.findById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Sector>> getAllSectors(@RequestHeader("Authorization") String token) {
        log.info("Sector controller getAllSectors method");
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<Sector> all = this.sectorService.getAllSectors();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<List<OfferedService>> getOfferedServicesForSector(
        @RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<OfferedService> services = this.sectorService.getOfferedServicesForSector(id);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Sector> deleteById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<Company> all = companyService.findAll();
        for(Company c: all){
            if( c.getSectorList()!= null && !c.getSectorList().isEmpty()){
            for(Sector s: c.getSectorList()){
                if(s.getId().equals(id)){
                    this.companyService.deleteSectorFromCompany(c.getId(),id);
                }
            }}
            companyService.update(c);
        }
        Sector s= this.sectorService.deleteById(id);
        return new ResponseEntity<Sector>(s, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest request)
        throws Exception {
        String token = this.sectorService.authenticateUser(request);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }
}
