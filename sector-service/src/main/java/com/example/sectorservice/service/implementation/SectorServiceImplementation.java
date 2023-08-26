package com.example.sectorservice.service.implementation;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.repository.SectorRepository;
import com.example.sectorservice.service.SectorService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import java.util.Base64;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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

    @Override
    public List<Sector> getAllSectors() {
        return this.sectorRepository.findAll();
    }

    public Boolean validateToken(String token, String secretKey) throws Exception {
        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
       String payloadChunks []= payload.split(":|,");
       String username ="";
        try {
            username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.substring(7)).getBody().getSubject();
        }catch (Exception e){
            throw new Exception("Could not verify JWT token integrity!");
        }

        return true;
    }

}
