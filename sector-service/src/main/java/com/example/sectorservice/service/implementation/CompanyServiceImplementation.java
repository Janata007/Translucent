package com.example.sectorservice.service.implementation;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.repository.CompanyRepository;
import com.example.sectorservice.repository.SectorRepository;
import com.example.sectorservice.service.CompanyService;
import java.util.ArrayList;
import java.util.List;
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
    public void deleteById(Long id) {
        Company company = this.companyRepository.findById(id).orElseThrow();
        this.companyRepository.deleteById(id);
    }

    @Override
    public Company deleteSectorFromCompany(Long companyId, Long sectorId) {
        Company company = this.companyRepository.findById(companyId).orElseThrow();
        Sector sector = this.sectorRepository.findById(sectorId).orElseThrow();
        company.removeSector(sector);
        this.companyRepository.save(company);
        return company;
    }

    @Override
    public Company addSectorToCompany(Long companyId, Long sectorId) {
        Sector sector = this.sectorRepository.findById(sectorId).orElseThrow();
        Company company = this.companyRepository.findById(companyId).orElseThrow();
        company.addSector(sector);
        this.companyRepository.save(company);
        return company;
    }

    @Override
    public List<OfferedService> getOfferedServicesForCompany(Long companyId) {
        Company c = this.companyRepository.findById(companyId).orElseThrow();
        List<Sector> sectors = c.getSectorList();
        List<OfferedService> services = new ArrayList<>();
        for (Sector s : sectors) {
            services.addAll(s.getOfferedServices());
        }
        return services;
    }

    @Override
    public List<Company> getCompaniesForNeededService(OfferedService neededSservice) {
        List<Company> existing = this.companyRepository.findAll();
        List<Company> companies = new ArrayList<>();
        for (Company c : existing) {
            List<OfferedService> services = getOfferedServicesForCompany(c.getId());
            for (OfferedService s : services) {
                if (s.equals(neededSservice)) {
                    companies.add(c);
                }
            }
        }
        return companies;
    }
}
