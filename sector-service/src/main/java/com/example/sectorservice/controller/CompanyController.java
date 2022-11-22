package com.example.sectorservice.controller;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.service.implementation.CompanyServiceImplementation;
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

    @PostMapping("/")
    public Company saveNewCompany(@RequestBody Company company) {
        return this.companyService.save(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable Long id) {
        return this.companyService.findById(id);
    }
}
