package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.crud.tasksplitter.entities.Role;
import pl.crud.tasksplitter.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
