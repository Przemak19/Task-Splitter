package pl.crud.tasksplitter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.crud.tasksplitter.dto.JWTAuthenticationResponse;
import pl.crud.tasksplitter.dto.RefreshTokenRequest;
import pl.crud.tasksplitter.dto.SignInRequest;
import pl.crud.tasksplitter.dto.SignUpRequest;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.services.AuthenticationService;

@RestController
@RequestMapping("/task-splitter/main/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
