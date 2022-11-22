package com.example.sectorservice.service;

import com.example.sectorservice.entity.Company;

public interface CompanyService {
    Company save(Company company);
    Company findById(Long id);
}
