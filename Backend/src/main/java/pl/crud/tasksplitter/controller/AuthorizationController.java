package pl.crud.tasksplitter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.crud.tasksplitter.dto.LoginRequest;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.dto.UserDto;
import pl.crud.tasksplitter.service.interf.UserService;

@RestController
@RequestMapping("/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserDto registrationRequest) {
        return ResponseEntity.ok(userService.registerUser(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }
}
