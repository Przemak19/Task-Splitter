package pl.crud.tasksplitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.crud.tasksplitter.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompanyTaskDto {
    private Long id;

    private String name;
    private String description;
    private LocalDateTime endDateTime;
    private TaskStatus taskStatus;
    private String imageUrl;
    private CompanyDto company;
    private UserDto user;
    private List<ReviewDto> review;
    private LocalDateTime createdAt;
}
