package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyMembership;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<CompanyMembership> getAllCompanyMembershipsByCompanyId(Long companyId);
}
