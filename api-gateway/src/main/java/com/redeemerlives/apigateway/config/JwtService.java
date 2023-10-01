//package com.redeemerlives.apigateway.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Base64;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//    private final String SECRET_KEY = "8o7g0El9ZgHmzx8289A54NCZmUMaHYuf8289A544652A8F8BDC68F3D3CE356KDZaMxXv7mxR8=";
//
//    public String extractUsername(String jwt) {
//        return extractClaim(jwt, Claims::getSubject);
//    }
//
//    private <T> T extractClaim(String jwt, Function<Claims, T> claimsTFunction) {
//        Claims claims = getAllClaims(jwt);
//        return claimsTFunction.apply(claims);
//    }
//
//    private Claims getAllClaims(String jwt) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(jwt)
//                .getBody();
//    }
//
////    public String generateJwtToken()
//
//    private Key getSignInKey() {
//        byte[] decodes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(decodes);
//    }
//}
