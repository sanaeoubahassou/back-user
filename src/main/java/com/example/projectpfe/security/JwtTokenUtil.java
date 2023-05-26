package com.example.projectpfe.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;//io.jsonwebtoken
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final long serialVersionUID = -2550185165626007488L;
    @Autowired
    private ObjectMapper mapper;
    //@Value("${security.jwt.secret}")
    //private String secret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(
            String token,
            Function<Claims, T> claimsResolver
    ) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(String autId) throws JsonProcessingException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authId", autId);
        return doGenerateToken(autId, claims);
    }

    private String doGenerateToken(String autId, Map<String, Object> claims)
            throws JsonProcessingException {
        return Jwts
                .builder()
                .setSubject(autId)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)
                )
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    //validate token
    public Boolean validateToken(String token, String subject) {
        final String username = getUsernameFromToken(token);
        return (username.equals(subject) && !isTokenExpired(token));
    }
}

