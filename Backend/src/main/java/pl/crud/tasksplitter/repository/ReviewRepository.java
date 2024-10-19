package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.crud.tasksplitter.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
