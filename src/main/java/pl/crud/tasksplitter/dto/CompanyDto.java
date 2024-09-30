package pl.crud.tasksplitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.crud.tasksplitter.entities.Address;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.CompanyTask;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;

    private String companyName;
    private String companyDescription;
    private AddressDto address;
    private List<CompanyTaskDto> tasks;
    private List<CompanyMembershipDto> companyMemberships;
    private LocalDateTime createdAt;
}
