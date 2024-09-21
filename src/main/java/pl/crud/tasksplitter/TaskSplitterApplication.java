package pl.crud.tasksplitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.crud.tasksplitter.entities.Role;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.repository.UserRepository;

@SpringBootApplication
public class TaskSplitterApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TaskSplitterApplication.class, args);
    }

    public void run(String... args) throws Exception {
        User adminAccount = userRepository.findByRole(Role.ADMIN);

        if(adminAccount == null) {
            User user = new User();

            user.setEmail("admin@gmail.com");
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));

            userRepository.save(user);
        }
    }

}
