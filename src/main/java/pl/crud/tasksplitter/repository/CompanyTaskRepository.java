package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyTask;

import java.util.List;

public interface CompanyTaskRepository extends JpaRepository<CompanyTask, Long> {

    List<CompanyTask> findAllByCompanyId(Long companyId);

    List<CompanyTask> findAllByUserId(Long userId);

    List<CompanyTask> findByNameContainingOrDescriptionContaining(String nameSearchText, String descriptionSearchText);
}
