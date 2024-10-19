package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<CompanyMembership> getAllCompanyMembershipsById(Long userId);

}
