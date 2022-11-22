package com.example.sectorservice.service.implementation;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.repository.CompanyRepository;
import com.example.sectorservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImplementation implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return this.companyRepository.save(company);
    }

    @Override
    public Company findById(Long id) {
        return this.companyRepository.findById(id).orElseThrow();
    }
}
