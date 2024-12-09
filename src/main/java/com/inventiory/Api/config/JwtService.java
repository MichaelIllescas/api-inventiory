
package com.inventiory.Api.config;

import com.inventiory.Api.Entity.User;
import com.inventiory.Api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author jonii
 */
@Service
public class JwtService {
    
    @Autowired
    private UserRepository userRepository;
    
    private final String SECRET_KEY= "e820871021b352a2ba5e11f2509e1b9a75e9164eb6a9968b319c99a567260519";

    public String extractUsername(String token) {
        return extractCliam(token, Claims::getSubject);
    }
    
    public <T> T extractCliam(String token, Function <Claims, T> claimsTFunction  ){
        final Claims claims= extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    
    
    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte [] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    ){
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public String generateToken(UserDetails userDetails){
        return generateToken( new HashMap<>(), userDetails) ;
    }
    
    public boolean isTokenValid( String token, UserDetails userDetails){
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationToken(token).before(new Date());
    }

    private Date extractExpirationToken(String token) {
         return extractCliam(token, Claims::getExpiration);
    }
    
    
    public User extractUser (HttpServletRequest requestConToken){
    
          String authorizationHeader = requestConToken.getHeader("Authorization");
          String token = authorizationHeader.substring(7);
          String email = extractUsername(token);
          User user= userRepository.findByEmail(email).orElse(null);
          return user;
    }
    
    
}
