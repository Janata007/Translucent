package com.example.sectorservice.service.implementation;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.repository.CompanyRepository;
import com.example.sectorservice.repository.SectorRepository;
import com.example.sectorservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImplementation implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public Company save(Company company) {
        return this.companyRepository.save(company);
    }

    @Override
    public Company findById(Long id) {
        return this.companyRepository.findById(id).orElseThrow();
    }

    @Override
    public Company deleteById(Long id) {
        Company company = this.companyRepository.findById(id).orElseThrow();
        this.companyRepository.deleteById(id);
        return company;
    }

    @Override
    public Company deleteSectorFromCompany(Long companyId, Long sectorId) {
        Company company = this.companyRepository.findById(companyId).orElseThrow();
        Sector sector = this.sectorRepository.findById(sectorId).orElseThrow();
        company.removeSector(sector);
        return company;
    }

    @Override
    public Company addSectorToCompany(Long companyId, Long sectorId) {
        Sector sector = this.sectorRepository.findById(sectorId).orElseThrow();
        Company company = this.companyRepository.findById(companyId).orElseThrow();
        company.addSector(sector);
        return company;
    }
}
