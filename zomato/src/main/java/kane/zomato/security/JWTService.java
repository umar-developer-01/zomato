package kane.zomato.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kane.zomato.dto.JwtDto;
import kane.zomato.entity.User;
import kane.zomato.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class JWTService {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", user.getRoles()) // Set<Role>
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 6))
                .signWith(getSecretKey())
                .compact();
    }


    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public Long getUserIdFromToken(String token) {
        return Long.valueOf(getAllClaims(token).getSubject());
    }


    public String getEmailFromToken(String token) {
        return getAllClaims(token).get("email", String.class);
    }


    public List<Role> getRolesFromToken(String token) {
        List<String> roles = getAllClaims(token).get("roles", List.class);
        return roles.stream().map(Role::valueOf).toList();

        //Role::valueOf is a method reference
        //.map(role -> Role.valueOf(role))
        //Role.valueOf("ADMIN") → Role.ADMIN
        //Role.valueOf("USER")  → Role.USER
        //"roles": ["ADMIN", "USER"]
        //List<Role> = [Role.ADMIN, Role.USER]
    }

    public JwtDto isTokenValid(String token) {
        try {
            getAllClaims(token);
            return new JwtDto(true, "Approved");

        } catch (ExpiredJwtException e) {
            log.warn("JWT expired");
            return new JwtDto(false, "Refresh Token Expired");

        } catch (JwtException e) {
            log.warn("JWT invalid");
            return new JwtDto(false, "Invalid Refresh Token");
        }
    }

}
