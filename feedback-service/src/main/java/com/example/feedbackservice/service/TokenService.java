package com.example.feedbackservice.service;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

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
