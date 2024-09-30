package pl.crud.tasksplitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private String token;
    private String role;
    private String expirationTime;

    private int totalPage;
    private long totalElement;

    private AddressDto address;

    private UserDto user;
    private List<UserDto> userList;

    private CompanyDto company;
    private List<CompanyDto> companyList;

    private CompanyTaskDto companyTask;
    private List<CompanyTaskDto> companyTaskList;

    private ReviewDto review;
    private List<ReviewDto> reviewList;
}
