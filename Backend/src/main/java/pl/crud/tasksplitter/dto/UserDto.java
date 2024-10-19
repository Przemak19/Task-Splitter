package pl.crud.tasksplitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private CompanyDto ownedCompany;
    private AddressDto address;
    private LocalDateTime createdAt;
}
