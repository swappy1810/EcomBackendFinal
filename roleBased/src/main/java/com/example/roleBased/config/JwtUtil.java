package com.example.roleBased.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private static final String SECRET_KEY = "learn_programming_yourself";

    private static final int TOKEN_VALIDITY  = 3600 * 5;


    //get username from token
    public static String extractUsername(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    //get Claim From Token
    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver){
        final Claims claims =  getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    //get all claims from token
    private static Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //validate token from username
    public Boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //checking expiry of token
    private Boolean isTokenExpired(String token){
        final Date expiredDate = getExpirationDateFromToken(token);
        return expiredDate.before(new Date());
    }

    //get expiration date from token
    private Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    //generate token from username,token validity and signature algorithm
    public static String createToken(Map<String,Object> claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact()
                ;
    }
    public static String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }
}
