package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.crud.tasksplitter.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
