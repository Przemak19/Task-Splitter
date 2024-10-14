package pl.crud.tasksplitter.service.interf;

import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.User;

public interface CompanyMembershipService {

    Response createCompanyMembershipInquiry(Long companyId);

    Response acceptCompanyMembership(Long companyMembershipId);

    Response deleteCompanyMembership(Long companyMembershipId);

    Response getAllMembersOfCompanyById(Long companyId);

    Response getAllRequestsToBeMemberById(Long companyId);

    Response getAllCompaniesOfUserById(Long userId);

    Response getAllRequestsOfUserById(Long userId);
}
