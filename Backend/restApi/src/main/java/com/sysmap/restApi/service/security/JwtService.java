package com.sysmap.restApi.service.security;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService implements IJwtService {
    // 2h
    private final long EXPIRATION_TIME = 7200000;
    private final String KEY = "4A614E645267556B58703273357538782F413F4428472B4B6250655368566D59";

    public String generateToken(UUID userId) {
        return Jwts
                .builder()
                // Send String
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // encrypt token
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                // exit token
                .compact();
    }

    // Method to validate token
    public boolean isValidToken(String token, String userId) {
        var claims = Jwts
                .parserBuilder()
                .setSigningKey(genSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        var sub = claims.getSubject();
        var tExpiration = claims.getExpiration();

        return (sub.equals(userId.toString()) && !tExpiration.before(new Date()));
    }

    // Method that generates key
    // Encryption Key Generator / No special character, always generate in Hex
    private Key genSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }
}
