package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.crud.tasksplitter.entities.CompanyTask;

public interface CompanyTaskRepository extends JpaRepository<CompanyTask, Long> {

}
