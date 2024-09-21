package pl.crud.tasksplitter.services;

import pl.crud.tasksplitter.dto.JWTAuthenticationResponse;
import pl.crud.tasksplitter.dto.RefreshTokenRequest;
import pl.crud.tasksplitter.dto.SignInRequest;
import pl.crud.tasksplitter.dto.SignUpRequest;
import pl.crud.tasksplitter.entities.User;

public interface AuthenticationService {

    User signUp(SignUpRequest signUpRequest);

    JWTAuthenticationResponse signIn(SignInRequest signInRequest);

    JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
