package com.bmsClone.authserver.jwtUtils;

import com.bmsClone.authserver.models.User;
import com.bmsClone.authserver.models.dtoModels.JWTPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public Claims getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getId());
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);
        return Jwts
                .builder()
                .setSubject(user.getId())
                .claim("user", JWTPayload.builder().id(user.getId()).admin(user.getAdmin()).build())
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public void validateToken(final String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
