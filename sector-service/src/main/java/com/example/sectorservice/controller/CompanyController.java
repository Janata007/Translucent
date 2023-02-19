package com.example.sectorservice.controller;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.service.implementation.CompanyServiceImplementation;
import com.example.sectorservice.service.implementation.SectorServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyServiceImplementation companyService;
    @Autowired
    private SectorServiceImplementation sectorService;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/")
    public ResponseEntity<Company> saveNewCompany(@RequestHeader("Authorization") String token,
                                                  @RequestBody Company company) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Company saved = this.companyService.save(company);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@RequestHeader("Authorization") String token,
                                                   @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Company found = this.companyService.findById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<String> deleteCompanyById(@RequestHeader("Authorization") String token,
                                                    @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        this.companyService.deleteById(id);
        return new ResponseEntity<>("sucess", HttpStatus.OK);
    }

    @PostMapping("/{companyId}/removeSector/{sectorId}")
    public ResponseEntity<Company> deleteSectorFromCompany(@RequestHeader("Authorization") String token,
                                                           @PathVariable Long companyId, @PathVariable Long sectorId) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Company toReturn = this.companyService.deleteSectorFromCompany(companyId, sectorId);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PostMapping("/{companyId}/addSector/{sectorId}")
    public ResponseEntity<Company> addSectorToCompany(@RequestHeader("Authorization") String token,
                                                      @PathVariable Long companyId, @PathVariable Long sectorId) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Company c = this.companyService.addSectorToCompany(companyId, sectorId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<List<OfferedService>> getOfferedServicesForCompany(
        @RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<OfferedService> serviceList = this.companyService.getOfferedServicesForCompany(id);
        return new ResponseEntity<>(serviceList, HttpStatus.OK);
    }

    @GetMapping("/serviceNeeded")
    public ResponseEntity<List<Company>> getCompaniesForNeededService(@RequestHeader("Authorization") String token,
                                                                      @Param("serviceNeeded")
                                                                      OfferedService serviceNeeded) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<Company> companyList = this.companyService.getCompaniesForNeededService(serviceNeeded);
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

}
