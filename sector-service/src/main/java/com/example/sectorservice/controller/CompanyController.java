package com.example.sectorservice.controller;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.service.implementation.CompanyServiceImplementation;
import com.example.sectorservice.service.implementation.SectorServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
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
    public Company saveNewCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return this.companyService.save(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return this.companyService.findById(id);
    }

    @PostMapping("/{id}/delete")
    public String deleteCompanyById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        this.companyService.deleteById(id);
        return "sucess";
    }

    @PostMapping("/{companyId}/removeSector/{sectorId}")
    public Company deleteSectorFromCompany(@RequestHeader("Authorization") String token, @PathVariable Long companyId,
                                           @PathVariable Long sectorId) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return this.companyService.deleteSectorFromCompany(companyId, sectorId);
    }

    @PostMapping("/{companyId}/addSector/{sectorId}")
    public Company addSectorToCompany(@RequestHeader("Authorization") String token, @PathVariable Long companyId,
                                      @PathVariable Long sectorId) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        Company company = companyService.findById(companyId);
        return this.companyService.addSectorToCompany(companyId, sectorId);
    }

    @GetMapping("/services/{id}")
    public List<OfferedService> getOfferedServicesForCompany(@RequestHeader("Authorization") String token,
                                                             @PathVariable Long id) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return this.companyService.getOfferedServicesForCompany(id);
    }

    @GetMapping("/serviceNeeded")
    public List<Company> getCompaniesForNeededService(@RequestHeader("Authorization") String token,
                                                      @Param("serviceNeeded") OfferedService serviceNeeded) {
        try {
            this.sectorService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return this.companyService.getCompaniesForNeededService(serviceNeeded);
    }

}
