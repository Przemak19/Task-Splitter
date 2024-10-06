package pl.crud.tasksplitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.crud.tasksplitter.entities.Address;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.CompanyTask;
import pl.crud.tasksplitter.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;
    private List<CompanyTaskDto> tasks;
    private List<CompanyMembershipDto> companyMemberships;
    private AddressDto address;
    private LocalDateTime createdAt;
}
