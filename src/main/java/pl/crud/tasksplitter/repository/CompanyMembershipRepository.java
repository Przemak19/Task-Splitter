package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.crud.tasksplitter.entities.CompanyMembership;

public interface CompanyMembershipRepository extends JpaRepository<CompanyMembership, Long> {
    boolean existsByCompanyIdAndUserId(Long companyId, Long userId);
}
