package com.example.sectorservice.service.implementation;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.repository.SectorRepository;
import com.example.sectorservice.service.SectorService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import java.util.Base64;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;

@Service
public class SectorServiceImplementation implements SectorService {
    private final SectorRepository sectorRepository;

    public SectorServiceImplementation(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public Sector save(Sector sector) {
        return sectorRepository.save(sector);
    }

    @Override
    public Sector findById(Long id) {
        return sectorRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find sector"));
    }

    @Override
    public List<OfferedService> getOfferedServicesForSector(Long id) {
        Sector sector = this.sectorRepository.findById(id).orElseThrow();
        return sector.getOfferedServices();
    }
    public Boolean validateToken(String token, String secretKey) throws Exception {
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
