package pl.crud.tasksplitter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.crud.tasksplitter.dto.LoginRequest;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.dto.UserDto;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.enums.Role;
import pl.crud.tasksplitter.exception.InvalidCredentialsException;
import pl.crud.tasksplitter.mapper.EntityDtoMapper;
import pl.crud.tasksplitter.repository.UserRepository;
import pl.crud.tasksplitter.security.JWTService;
import pl.crud.tasksplitter.service.interf.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response registerUser(UserDto registrationRequest) {
        Role role = Role.USER;

        if(registrationRequest.getRole() != null && registrationRequest.getRole().equalsIgnoreCase("ADMIN")) {
            role = Role.ADMIN;
        }

        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(role)
                .build();

        User savedUser = userRepository.save(user);
        UserDto userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);

        return Response.builder()
                .status(200)
                .message("User successfully registered")
                .user(userDto)
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid email"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }
        String token = jwtService.generateToken(user);

        return Response.builder()
                .status(200)
                .message("User successfully logged in")
                .token(token)
                .expirationTime("7 days")
                .role(user.getRole().name())
                .build();
    }

    @Override
    public Response getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userList = users.stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .message("Successful")
                .userList(userList)
                .build();
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        log.info("User email: {}", email);

        return userRepository.findByEmail(email).orElseThrow(() -> new InvalidCredentialsException("User not found"));
    }

    @Override
    public Response getUserInfoAndTasks() {
        User user = getLoginUser();
        UserDto userDto = entityDtoMapper.mapUserToDtoWithTasksAndMembership(user);

        return Response.builder()
                .status(200)
                .message("Successful")
                .user(userDto)
                .build();
    }

    @Override
    public User saveUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User saveUserCompanyMembership(CompanyMembership companyMembership, User user) {
        List<CompanyMembership> companyMemberships = userRepository.getAllCompanyMembershipsById(user.getId());

        companyMemberships.add(companyMembership);
        user.setCompanyMemberships(companyMemberships);

        return userRepository.save(user);
    }

    @Override
    public Boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }

    @Override
    public Boolean isUser(User user) {
        return user.getRole() == Role.USER;
    }

    @Override
    public Company getAdminCompany(User user)
    {
        return user.getRole() == Role.ADMIN ? user.getOwnedCompany() : null;
    }
}
