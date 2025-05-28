package org.mrstm.uberauthproject.services;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String secret;


    private String createToken(Map<String , Object> payload , String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder().claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .subject(username)
                .signWith(key)
                .compact();
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String , Object> payload = new HashMap<>();
        payload.put("username", "john");
        payload.put("password", "secret");
        payload.put("role", "admin");
        String token = createToken(payload , "john");
        System.out.println(token);

    }
}
