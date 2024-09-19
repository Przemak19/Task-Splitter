package pl.crud.tasksplitter.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
