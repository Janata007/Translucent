package com.example.sectorservice.controller;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.service.implementation.CompanyServiceImplementation;
import com.example.sectorservice.service.implementation.SectorServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyServiceImplementation companyService;
    @Autowired
    private SectorServiceImplementation sectorService;

    @PostMapping("/")
    public Company saveNewCompany(@RequestBody Company company) {
        return this.companyService.save(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable Long id) {
        return this.companyService.findById(id);
    }

    @PostMapping("/{id}/delete")
    public Company deleteCompanyById(@PathVariable Long id) {
        return this.companyService.deleteById(id);
    }

    @PostMapping("/{companyId}/removeSector/{sectorId}")
    public Company deleteSectorFromCompany(@PathVariable Long companyId, @PathVariable Long sectorId) {
        return this.companyService.deleteSectorFromCompany(companyId, sectorId);
    }

    @PostMapping("/{companyId}/addSector/{sectorId}")
    public Company addSectorToCompany(@PathVariable Long companyId, @PathVariable Long sectorId) {
        Company company = companyService.findById(companyId);
        return this.companyService.addSectorToCompany(companyId, sectorId);
    }
}
