package com.Dinesh.CampusCribe.Util;

import com.Dinesh.CampusCribe.models.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
   private final String secretKey = "my-super-secret-key-for-jwt-signing";
   private final SecretKey key= Keys.hmacShaKeyFor(secretKey.getBytes());

   public String generateToken(String userName){
       return Jwts.builder()
               .setSubject(userName)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+(1000*60*60*60)))
               .signWith(key, SignatureAlgorithm.HS256)
               .compact();
   }
   public Claims claims(String token){
       return Jwts.parserBuilder()
               .setSigningKey(key)
               .build()
               .parseClaimsJws(token)
               .getBody();
   }
   public String getUserName(String token){
       return claims(token).getSubject();
   }

    public boolean validateToken(String userName, UserDetails userDetails,String token) {
       return userName.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
       return claims(token).getExpiration().before(new Date());
    }
}
