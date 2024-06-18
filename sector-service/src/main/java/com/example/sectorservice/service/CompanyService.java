package com.example.sectorservice.service;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.entity.OfferedService;
import java.util.List;

public interface CompanyService {
    Company save(Company company);

    Company findById(Long id);

    void deleteById(Long id);
    Company update(Company company);
    List<Company> findAll();

    Company deleteSectorFromCompany(Long companyId, Long sectorId);

    Company addSectorToCompany(Long companyId, Long sectorId);
    List<OfferedService> getOfferedServicesForCompany(Long companyId);
    List<Company> getCompaniesForNeededService(OfferedService neededService);
    List<Company> getAllCompanies();
}
