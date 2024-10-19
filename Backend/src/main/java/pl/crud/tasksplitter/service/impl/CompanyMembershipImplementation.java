package pl.crud.tasksplitter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.exception.NotFoundException;
import pl.crud.tasksplitter.repository.CompanyMembershipRepository;
import pl.crud.tasksplitter.repository.CompanyRepository;
import pl.crud.tasksplitter.repository.UserRepository;
import pl.crud.tasksplitter.service.interf.CompanyMembershipService;
import pl.crud.tasksplitter.service.interf.CompanyService;
import pl.crud.tasksplitter.service.interf.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyMembershipImplementation implements CompanyMembershipService {

    private final UserService userService;
    private final CompanyMembershipRepository companyMembershipRepository;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final UserRepository userRepository;

    @Override
    public Response createCompanyMembershipInquiry(Long companyId) {
        User user = userService.getLoginUser();
        String message = "";

        if(userService.isUser(user)) {
            CompanyMembership companyMembership = new CompanyMembership();

            if(companyMembershipRepository.existsByCompanyIdAndUserId(companyId, user.getId())) {
                message = "This membership request already exists";
            } else {
                Company company = null;
                if(companyId != null) {
                    company = companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Company not found"));
                }

                if(user != null) {companyMembership.setUser(user);}
                if(company != null) {companyMembership.setCompany(company);}

                companyMembership.setAccepted(false);

                message = "Membership request created";
                companyMembershipRepository.save(companyMembership);

                userService.saveUserCompanyMembership(companyMembership, user);

                companyService.saveCompanyMembership(companyMembership, company);
            }
            return Response.builder()
                    .status(200)
                    .message(message)
                    .build();
        }
        return Response.builder()
                .status(403)
                .message("Forbidden")
                .build();
    }

    @Override
    public Response acceptCompanyMembership(Long companyMembershipId) {
        User user = userService.getLoginUser();
        Company company = userService.getAdminCompany(user);
        CompanyMembership companyMembership = null;

        if(companyMembershipId != null) {
            companyMembership = companyMembershipRepository.findById(companyMembershipId).orElseThrow(() -> new NotFoundException("CompanyMembership not found"));
        }

        if(companyMembership.getCompany().getId().equals(company.getId())) {
            companyMembership.setAccepted(true);

            return Response.builder()
                    .status(200)
                    .message("Company Membership accepted")
                    .build();
        }

        return Response.builder()
                .status(403)
                .message("Forbidden")
                .build();
    }

    @Override
    public Response deleteCompanyMembership(Long companyMembershipId) {
        return null;
    }

    @Override
    public Response getAllMembersOfCompanyById(Long companyId) {
        return null;
    }

    @Override
    public Response getAllRequestsToBeMemberById(Long companyId) {
        return null;
    }

    @Override
    public Response getAllCompaniesOfUserById(Long userId) {
        return null;
    }

    @Override
    public Response getAllRequestsOfUserById(Long userId) {
        return null;
    }
}
