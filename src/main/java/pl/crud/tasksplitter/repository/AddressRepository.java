package pl.crud.tasksplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.crud.tasksplitter.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
