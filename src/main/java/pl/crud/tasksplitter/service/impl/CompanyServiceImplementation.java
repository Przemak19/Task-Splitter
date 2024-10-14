package pl.crud.tasksplitter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.crud.tasksplitter.dto.CompanyDto;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.repository.CompanyRepository;
import pl.crud.tasksplitter.service.interf.CompanyService;
import pl.crud.tasksplitter.service.interf.UserService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImplementation implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Response createAndUpdateCompany(CompanyDto companyDto) {
        User user = userService.getLoginUser();
        Company company = new Company();
        String message = "";

        if(userService.isAdmin(user)) {
            if(user.getOwnedCompany() != null) {
                company = user.getOwnedCompany();
            }
            message = (user.getOwnedCompany() == null) ? "Company created" : "Company updated";

            if(companyDto.getCompanyName() != null) company.setCompanyName(companyDto.getCompanyName());
            if(companyDto.getCompanyDescription() != null) company.setCompanyDescription(companyDto.getCompanyDescription());

            companyRepository.save(company);

            user.setOwnedCompany(company);
            userService.saveUser(user);
        }

        return Response.builder()
                .status(200)
                .message(message)
                .build();
    }

    @Override
    public Company saveCompanyMembership(CompanyMembership companyMembership, Company company) {
        List<CompanyMembership> companyMemberships = companyRepository.getAllCompanyMembershipsByCompanyId(company.getId());

        companyMemberships.add(companyMembership);
        company.setCompanyMemberships(companyMemberships);

        return companyRepository.save(company);
    }
}
