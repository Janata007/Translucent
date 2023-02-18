package com.example.feedbackservice.service;

import io.jsonwebtoken.Jwts;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenService {

    public Boolean validateToken(String token, String secretKey) throws Exception {
        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String payloadChunks[] = payload.split(":|,");
        String username = "";
        try {
            username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.substring(7)).getBody().getSubject();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }
}
