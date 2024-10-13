package pl.crud.tasksplitter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.crud.tasksplitter.dto.CompanyTaskDto;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyTask;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.enums.TaskStatus;
import pl.crud.tasksplitter.exception.NotFoundException;
import pl.crud.tasksplitter.mapper.EntityDtoMapper;
import pl.crud.tasksplitter.repository.CompanyRepository;
import pl.crud.tasksplitter.repository.CompanyTaskRepository;
import pl.crud.tasksplitter.repository.ReviewRepository;
import pl.crud.tasksplitter.repository.UserRepository;
import pl.crud.tasksplitter.service.AwsS3Service;
import pl.crud.tasksplitter.service.interf.CompanyTaskService;
import pl.crud.tasksplitter.service.interf.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyTaskServiceImplementation implements CompanyTaskService {

    private final CompanyTaskRepository companyTaskRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final EntityDtoMapper entityDtoMapper;
    private final AwsS3Service awsS3Service;

    @Override
    public Response createTask(MultipartFile image, String name, String description, java.time.LocalDateTime endDateTime) {
        User user = userService.getLoginUser();
        Company company = userService.getAdminCompany(user);

        if (company != null) {
            String taskImageUrl = awsS3Service.saveImageToS3Bucket(image);

            CompanyTask companyTask = new CompanyTask();

            companyTask.setCompany(company);
            companyTask.setName(name);
            companyTask.setDescription(description);
            companyTask.setImageUrl(taskImageUrl);
            companyTask.setTaskStatus(TaskStatus.NOT_STARTED);
            companyTask.setEndDateTime(endDateTime);

            companyTaskRepository.save(companyTask);

            return shortTaskResponse(200, "Task successfully created");
        } else {
            return shortTaskResponse(409, "Task could not be created");
        }
    }

    @Override
    public Response updateTask(Long taskId, Long userId, TaskStatus taskStatus, MultipartFile image, String name, String description, LocalDateTime endDateTime) {
        CompanyTask companyTask = companyTaskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));

        if(checkTaskBelongCurrentCompany(companyTask)) {
            User user = null;
            String taskImageUrl = null;

            if(userId != null) {
                user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            }
            if(image != null) {
                taskImageUrl = awsS3Service.saveImageToS3Bucket(image);
            }

            if(user != null) {companyTask.setUser(user);}
            if(taskImageUrl != null) {companyTask.setImageUrl(taskImageUrl);}
            if(taskStatus != null) {companyTask.setTaskStatus(taskStatus);}
            if(name != null) {companyTask.setName(name);}
            if(description != null) {companyTask.setDescription(description);}
            if(endDateTime != null) {companyTask.setEndDateTime(endDateTime);}

            companyTaskRepository.save(companyTask);

            return shortTaskResponse(200, "Task successfully updated");
        }else {
            return shortTaskResponse(409, "Task could not be updated");
        }
    }

    private Response shortTaskResponse(int status, String message) {
        return Response.builder()
                .status(status)
                .message(message)
                .build();
    }

    private Boolean checkTaskBelongCurrentCompany(CompanyTask companyTask) {
        Company company = userService.getAdminCompany(userService.getLoginUser());

        return companyTask.getCompany().getId().equals(company.getId());
    }

    @Override
    public Response deleteTask(Long taskId) {
        CompanyTask companyTask = companyTaskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));

        if(checkTaskBelongCurrentCompany(companyTask)) {
            companyTaskRepository.delete(companyTask);

            return shortTaskResponse(200, "Task successfully deleted");
        }
        return shortTaskResponse(409, "Task could not be deleted");
    }

    @Override
    public Response getTaskById(Long taskId) {
        CompanyTask companyTask = companyTaskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));

        CompanyTaskDto companyTaskDto = entityDtoMapper.mapCompanyTaskToDtoWithUserCompanyReview(companyTask);

        return Response.builder()
                .status(200)
                .companyTask(companyTaskDto)
                .build();
    }

    @Override
    public Response getAllTasks() {
        List<CompanyTaskDto> companyTaskDtos = companyTaskRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(entityDtoMapper::mapCompanyTaskToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .companyTaskList(companyTaskDtos)
                .build();
    }

    @Override
    public Response getTasksByCompanyId(Long companyId) {
        List<CompanyTask> companyTasks = companyTaskRepository.findAllByCompanyId(companyId);

        if(companyTasks.isEmpty()) {
            throw new NotFoundException("No tasks found for this company");
        }

        List<CompanyTaskDto> companyTaskDtoList = companyTasks.stream()
                .map(entityDtoMapper::mapCompanyTaskToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .companyTaskList(companyTaskDtoList)
                .build();
    }

    @Override
    public Response getTasksByUserId(Long userId) {
        List<CompanyTask> companyTasks = companyTaskRepository.findAllByUserId(userId);

        if(companyTasks.isEmpty()) {
            throw new NotFoundException("No tasks found for this user");
        }

        List<CompanyTaskDto> companyTaskDtoList = companyTasks.stream()
                .map(entityDtoMapper::mapCompanyTaskToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .companyTaskList(companyTaskDtoList)
                .build();
    }

    @Override
    public Response searchTask(String searchValue) {
        List<CompanyTask> companyTasks = companyTaskRepository.findByNameContainingOrDescriptionContaining(searchValue, searchValue);

        if(companyTasks.isEmpty()) {
            throw new NotFoundException("No tasks found");
        }

        List<CompanyTaskDto> companyTaskDtoList = companyTasks.stream()
                .map(entityDtoMapper::mapCompanyTaskToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .companyTaskList(companyTaskDtoList)
                .build();
    }
}
