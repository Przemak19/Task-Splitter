package pl.crud.tasksplitter.service.interf;

import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.entities.Review;
import pl.crud.tasksplitter.enums.TaskStatus;

public interface CompanyTaskService {

    Response createTask(MultipartFile image, String name, String description, LocalDateTime endDateTime);
    Response updateTask(Long taskId, Long userId, TaskStatus taskStatus, MultipartFile image, String name, String description, LocalDateTime endDateTime);
    Response deleteTask(Long taskId);
    Response getTaskById(Long taskId);
    Response getAllTasks();
    Response getTasksByCompanyId(Long companyId);
    Response getTasksByUserId(Long userId);
    Response searchTask(String searchValue);
}
