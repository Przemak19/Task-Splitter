package pl.crud.tasksplitter.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.exception.NotFoundException;
import pl.crud.tasksplitter.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(("User with email " + email + " not found")));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
