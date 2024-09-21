package pl.crud.tasksplitter.services;

import org.springframework.security.core.userdetails.UserDetails;
import pl.crud.tasksplitter.entities.User;

import java.util.HashMap;
import java.util.Map;

public interface JWTService {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);
}
