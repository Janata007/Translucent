package com.example.sectorservice.controller;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import com.example.sectorservice.entity.JwtRequest;
import com.example.sectorservice.entity.JwtResponse;
import com.example.sectorservice.entity.OfferedService;
import com.example.sectorservice.entity.Sector;
import com.example.sectorservice.service.implementation.SectorServiceImplementation;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import java.util.Base64;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sector")
public class SectorController {
    @Autowired
    private SectorServiceImplementation sectorService;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/")
    public Sector saveSector(@RequestHeader("Authorization") String token, @RequestBody Sector sector) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        return sectorService.save(sector);
    }

    @GetMapping("/{id}")
    public Sector findById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        return sectorService.findById(id);
    }

    @GetMapping("/services/{id}")
    public List<OfferedService> getOfferedServicesForSector(@RequestHeader("Authorization") String token,
                                                            @PathVariable Long id) {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        return sectorService.getOfferedServicesForSector(id);
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestHeader("Authorization") String token, @RequestBody JwtRequest jwtRequest)
        throws Exception {
        try {
            this.tokenValidated(token);
        } catch (Exception e) {
        }
        //todo: redirect to user service auth
        return new JwtResponse("token");
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
