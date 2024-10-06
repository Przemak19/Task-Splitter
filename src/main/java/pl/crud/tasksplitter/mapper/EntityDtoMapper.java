package pl.crud.tasksplitter.mapper;

import org.springframework.stereotype.Component;
import pl.crud.tasksplitter.dto.*;
import pl.crud.tasksplitter.entities.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoMapper {

    public UserDto mapUserToDtoBasic(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());

        return userDto;
    }

    public AddressDto mapAddressToDtoBasic(Address address) {
        AddressDto addressDto = new AddressDto();

        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setNumber(address.getNumber());
        addressDto.setCity(address.getCity());
        addressDto.setPostalCode(address.getPostalCode());
        addressDto.setCountry(address.getCountry());

        return addressDto;
    }

    public CompanyDto mapCompanyToDtoBasic(Company company) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setCompanyDescription(company.getCompanyDescription());

        return companyDto;
    }

    public CompanyTaskDto mapCompanyTaskToDtoBasic(CompanyTask companyTask) {
        CompanyTaskDto companyTaskDto = new CompanyTaskDto();

        companyTaskDto.setId(companyTask.getId());
        companyTaskDto.setName(companyTask.getName());
        companyTaskDto.setDescription(companyTask.getDescription());
        companyTaskDto.setEndDateTime(companyTask.getEndDateTime());
        companyTaskDto.setTaskStatus(companyTask.getTaskStatus().name());
        companyTaskDto.setImageUrl(companyTask.getImageUrl());

        return companyTaskDto;
    }

    public ReviewDto mapReviewToDtoBasic(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setRating(review.getRating());
        reviewDto.setCreatedBy(review.getCreatedBy());

        return reviewDto;
    }

    public CompanyMembershipDto mapCompanyMembershipToDto(CompanyMembership companyMembership) {
        CompanyMembershipDto companyMembershipDto = new CompanyMembershipDto();

        companyMembershipDto.setId(companyMembership.getId());

        companyMembershipDto.setAccepted(companyMembership.getAccepted());

        if(companyMembership.getUser() != null) {
            UserDto userDto = mapUserToDtoBasic(companyMembership.getUser());
            companyMembershipDto.setUser(userDto);
        }

        if(companyMembership.getCompany() != null) {
            CompanyDto companyDto = mapCompanyToDtoBasic(companyMembership.getCompany());
            companyMembershipDto.setCompany(companyDto);
        }

        return companyMembershipDto;
    }

    public UserDto mapUserToDtoBasicWithAddress(User user) {
        UserDto userDto = mapUserToDtoBasic(user);

        if(user.getAddress() != null) {
            AddressDto addressDto = mapAddressToDtoBasic(user.getAddress());
            userDto.setAddress(addressDto);
        }
        
        return userDto;
    }

    public CompanyTaskDto mapCompanyTaskToDtoWithUserCompanyReview(CompanyTask companyTask) {
        CompanyTaskDto companyTaskDto = mapCompanyTaskToDtoBasic(companyTask);

        if(companyTask.getCompany() != null) {
            CompanyDto companyDto = mapCompanyToDtoBasic(companyTask.getCompany());
            companyTaskDto.setCompany(companyDto);
        }

        if(companyTask.getUser() != null) {
            UserDto userDto = mapUserToDtoBasicWithAddress(companyTask.getUser());
            companyTaskDto.setUser(userDto);
        }

        if(companyTask.getReview() != null && !companyTask.getReview().isEmpty()) {
            companyTaskDto.setReview(companyTask.getReview()
            .stream()
                    .map(this::mapReviewToDtoBasic)
                    .collect(Collectors.toList()));
        }

        return companyTaskDto;
    }

    public UserDto mapUserToDtoWithTasksAndMembership(User user) {
        UserDto userDto = mapUserToDtoBasic(user);

        if(user.getTasks() != null && !user.getTasks().isEmpty()) {
            userDto.setTasks(user.getTasks()
            .stream()
                    .map(this::mapCompanyTaskToDtoWithUserCompanyReview)
                    .collect(Collectors.toList()));
        }

        if(user.getCompanyMemberships() != null && !user.getCompanyMemberships().isEmpty()) {
            userDto.setCompanyMemberships(user.getCompanyMemberships()
                    .stream()
                    .map(this::mapCompanyMembershipToDto)
                    .collect(Collectors.toList()));
        }

        return userDto;
    }

}
