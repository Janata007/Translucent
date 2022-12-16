package com.example.sectorservice.controller;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import com.example.sectorservice.entity.Company;
import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.service.implementation.CompanyServiceImplementation;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import java.util.Base64;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
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
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/")
    public Company saveNewCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        return this.companyService.save(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        return this.companyService.findById(id);
    }

    @PostMapping("/{id}/delete")
    public String deleteCompanyById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        this.companyService.deleteById(id);
        return "sucess";
    }

    @PostMapping("/{companyId}/removeSector/{sectorId}")
    public Company deleteSectorFromCompany(@RequestHeader("Authorization") String token, @PathVariable Long companyId,
                                           @PathVariable Long sectorId) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        return this.companyService.deleteSectorFromCompany(companyId, sectorId);
    }

    @PostMapping("/{companyId}/addSector/{sectorId}")
    public Company addSectorToCompany(@RequestHeader("Authorization") String token, @PathVariable Long companyId,
                                      @PathVariable Long sectorId) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        Company company = companyService.findById(companyId);
        return this.companyService.addSectorToCompany(companyId, sectorId);
    }

    @GetMapping("/services/{id}")
    public List<OfferedService> getOfferedServicesForCompany(@RequestHeader("Authorization") String token,
                                                             @PathVariable Long id) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        return this.companyService.getOfferedServicesForCompany(id);
    }

    @GetMapping("/serviceNeeded")
    public List<Company> getCompaniesForNeededService(@RequestHeader("Authorization") String token,
                                                      @Param("serviceNeeded") OfferedService serviceNeeded) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        return this.companyService.getCompaniesForNeededService(serviceNeeded);
    }

    protected Boolean tokenValidated(String token) throws Exception {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        SignatureAlgorithm sa = HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new Exception("Could not verify JWT token integrity!");
        }
        return true;
    }
}
