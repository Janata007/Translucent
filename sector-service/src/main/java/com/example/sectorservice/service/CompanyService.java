package com.example.sectorservice.service;

import com.example.sectorservice.entity.Company;

public interface CompanyService {
    Company save(Company company);

    Company findById(Long id);

    Company deleteById(Long id);

    Company deleteSectorFromCompany(Long companyId, Long sectorId);

    Company addSectorToCompany(Long companyId, Long sectorId);
}
